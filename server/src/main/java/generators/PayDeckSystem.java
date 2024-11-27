package generators;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import models.Client;
import models.PayDeck;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@AllArgsConstructor
@Getter
@Setter
public class PayDeckSystem {
    private List<PayDeck> payDecks;
    private PayDeck reservedPayDeck;
    private ExecutorService threadPools;

    public PayDeckSystem(List<PayDeck> payDecks, PayDeck reservedPayDeck, int threadPoolSize) {
        this.payDecks = payDecks;
        this.reservedPayDeck = reservedPayDeck;
        threadPools = Executors.newFixedThreadPool(threadPoolSize);
    }

    public void addClient(int id, Client client) {
        payDecks.get(id).addClient(client);
    }
    public void start() {
        // Put any initialization logic for PayDeckSystem here
        System.out.println("PayDeckSystem started.");
        // For example, you could start a thread pool or initialize any state
    }
}
