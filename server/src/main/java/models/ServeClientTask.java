package models;

import event_dispather.WebNotifier.WebNotifier;
import events.ServiceEvent;

public class ServeClientTask implements Runnable {
    private PayDeck payDeck;
    private Client client;
    private WebNotifier webNotifier;

    public ServeClientTask(PayDeck payDeck, Client client, WebNotifier webNotifier) {
        this.payDeck = payDeck;
        this.client = client;
        this.webNotifier = webNotifier;
    }

    @Override
    public void run() {
        if(!payDeck.getClientsQueue().contains(client)) {
            return;
        }

        try {
            long servingTime = client.getTicketsToBuy() * 1000L;
            ServiceEvent serviceEvent = new ServiceEvent(payDeck.getId(), servingTime);
            webNotifier.notify(serviceEvent);
            //Servicing
            Thread.sleep(servingTime);

            payDeck.getClientsQueue().remove(client);
        } catch (Exception e) {

        }
    }
}
