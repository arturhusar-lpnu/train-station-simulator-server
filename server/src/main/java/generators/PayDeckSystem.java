package generators;

import event_listeners.web.ServeClientService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import models.Client;
import models.PayDeck;
import models.ServeClientTask;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@AllArgsConstructor
@Getter
@Setter
public class PayDeckSystem {
    private List<PayDeck> payDecks;
    private PayDeck reservedPayDeck;
    private ExecutorService threadPools;
    private ServeClientService serveClientService;
    public PayDeckSystem(List<PayDeck> payDecks, PayDeck reservedPayDeck, int threadPoolSize, ServeClientService serveClientService) {
        this.payDecks = payDecks;
        this.reservedPayDeck = reservedPayDeck;
        threadPools = Executors.newFixedThreadPool(threadPoolSize);
        this.serveClientService = serveClientService;
    }
    public void paydeckServeClient(int payDeckId, Client client) {
        PayDeck payDeck = payDecks.stream().filter(p -> p.getId() == payDeckId).findFirst().orElse(null);
        if(payDeck == null) {
            return;
        }
        ServeClientTask task = new ServeClientTask(payDeck, client, serveClientService);
        threadPools.execute(task);
    }

    public void addClient(int id, Client client) {
        payDecks.get(id).addClient(client);
    }
}
