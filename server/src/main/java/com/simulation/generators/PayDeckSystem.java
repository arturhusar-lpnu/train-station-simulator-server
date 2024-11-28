package com.simulation.generators;

import com.simulation.events.CrashPaydeckEvent;
import com.simulation.services.ServeClientService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import com.simulation.models.Client;
import com.simulation.models.PayDeck;
import com.simulation.models.ServeClientTask;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@AllArgsConstructor
@Getter
@Setter
public class PayDeckSystem {
    private List<PayDeck> payDecks;
    private PayDeck reservedPayDeck;
    private ExecutorService threadPools;
    private ServeClientService serveClientService;
    private final ConcurrentHashMap<String, Future<?>> taskMap;

    public PayDeckSystem(int payDeckCount) {
        payDecks = new ArrayList<>(payDeckCount);
        for (int i = 0; i < payDeckCount; i++) {
            payDecks.add(new PayDeck());
        }

        reservedPayDeck = new PayDeck();
        reservedPayDeck.setWorking(false);

        threadPools = Executors.newFixedThreadPool(payDeckCount);
        taskMap = new ConcurrentHashMap<>();
    }

    public void payDeckServeClient(String payDeckId, Client client) {
        PayDeck payDeck = getPayDeckById(payDeckId);

        if(payDeck == null || client == null) {
            return; //throw NotFound
        }

        ServeClientTask task = new ServeClientTask(payDeck, client, serveClientService);
        Future<?> future = threadPools.submit(task);
        taskMap.put(payDeckId, future);
    }

    public PayDeck getPayDeckById(String payDeckId) {
        return payDecks.stream()
                .filter(p -> Objects.equals(p.getId(), payDeckId))
                .findFirst()
                .orElse(null);
    }

    public void setServeClientService(ServeClientService serveClientService) {
        this.serveClientService = serveClientService;
        payDecks.forEach(p -> p.setService(serveClientService));
    }
    //FIXed reserved payDeck
    public void interruptTask(PayDeck crashedPayDeck) {
        Future<?> future = taskMap.get(crashedPayDeck.getId());
        if (future != null) { //if pay deck does not serve a client its just crashing
            future.cancel(true);
            taskMap.remove(crashedPayDeck.getId());
        }

        //crashed pay deck is not working after crash
        crashedPayDeck.setWorking(false);

        //it is removed from pay decks
        payDecks.remove(crashedPayDeck);

        //reserved activated
        reservedPayDeck.setWorking(true);

        //reserved is now at the working list
        payDecks.add(reservedPayDeck);
        CrashPaydeckEvent crashPaydeckEvent = new CrashPaydeckEvent(crashedPayDeck, reservedPayDeck, LocalDateTime.now());

        //sends event on front to swap the
        serveClientService.sendCrashedDecks(crashPaydeckEvent);

        //the crashed becomes the reserved till next crash
        reservedPayDeck = crashedPayDeck;
    }

    public void shutdown() {
        threadPools.shutdownNow();
    }
}
