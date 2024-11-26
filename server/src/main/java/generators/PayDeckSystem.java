package generators;

import event_listeners.web.ServeClientService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import models.Client;
import models.PayDeck;
import models.ServeClientTask;

import java.util.List;
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
    private final ConcurrentHashMap<Integer, Future<?>> taskMap = new ConcurrentHashMap<>();

    public PayDeckSystem(List<PayDeck> payDecks, PayDeck reservedPayDeck, int threadPoolSize, ServeClientService serveClientService) {
        this.payDecks = payDecks;
        this.reservedPayDeck = reservedPayDeck;
        threadPools = Executors.newFixedThreadPool(threadPoolSize);
        this.serveClientService = serveClientService;
    }
    public void paydeckServeClient(int payDeckId, Client client) {
        PayDeck payDeck = getPayDeckById(payDeckId);

        if(payDeck == null) {
            return;
        }

        ServeClientTask task = new ServeClientTask(payDeck, client, serveClientService);
        Future<?> future = threadPools.submit(task);
        taskMap.put(payDeckId, future);
    }
    public PayDeck getPayDeckById(int payDeckId) {
        return payDecks.stream()
                .filter(p -> p.getId() == payDeckId)
                .findFirst()
                .orElse(null);
    }
    public void interruptTask(int payDeckId) {
        Future<?> future = taskMap.get(payDeckId);
        if (future != null) {
            future.cancel(true);
            taskMap.remove(payDeckId);
        }
    }

    public void addClient(int id, Client client) {
        payDecks.get(id).addClient(client);
    }

    public void shutdown() {
        threadPools.shutdownNow();
    }
}
