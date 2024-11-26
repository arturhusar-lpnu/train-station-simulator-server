package com.simulation.generators;

import com.simulation.services.ServeClientService;
import com.simulation.events.RecoveryPaydeckEvent;
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
    private final ConcurrentHashMap<String, Future<?>> taskMap = new ConcurrentHashMap<>();

    public PayDeckSystem(List<PayDeck> payDecks, PayDeck reservedPayDeck, int threadPoolSize) {
        this.payDecks = payDecks;
        this.reservedPayDeck = reservedPayDeck;
        reservedPayDeck.setWorking(false);
        threadPools = Executors.newFixedThreadPool(threadPoolSize);
    }

    public PayDeckSystem(int payDeckCount) {
        List<PayDeck> payDecks = new ArrayList<>(payDeckCount);
        for (int i = 0; i < payDeckCount; i++) {
            payDecks.add(new PayDeck());
        }
        reservedPayDeck = new PayDeck();
        threadPools = Executors.newFixedThreadPool(payDeckCount);
    }
    public void setRecoveredPayDeck(PayDeck recoveredPayDeck) {
        reservedPayDeck = recoveredPayDeck;
        RecoveryPaydeckEvent recoveryPaydeckEvent = new RecoveryPaydeckEvent(recoveredPayDeck, LocalDateTime.now());
        serveClientService.sendRecoveredDeckEvent(recoveryPaydeckEvent);
    }

    public void paydeckServeClient(String payDeckId, Client client) {
        PayDeck payDeck = getPayDeckById(payDeckId);

        if(payDeck == null) {
            return;
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
    //FIX reserved payDeck
    public void interruptTask(String payDeckId) {
        Future<?> future = taskMap.get(payDeckId);
        if (future != null) {
            future.cancel(true);
            taskMap.remove(payDeckId);
        }
        reservedPayDeck.setWorking(true);
    }


    public void addClient(int id, Client client) {
        payDecks.get(id).addClient(client);
    }

    public void shutdown() {
        threadPools.shutdownNow();
    }
}
