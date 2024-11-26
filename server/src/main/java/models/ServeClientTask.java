package models;

import services.ServeClientService;
import events.CrashPaydeckEvent;
import events.ServiceEvent;

import java.time.LocalDateTime;


public class ServeClientTask implements Runnable {
    private final PayDeck payDeck;
    private final Client client;
    private final ServeClientService serveClientService;

    public ServeClientTask(PayDeck payDeck, Client client, ServeClientService serveClientService) {
        this.payDeck = payDeck;
        this.client = client;
        this.serveClientService = serveClientService;
    }

    @Override
    public void run() {
        if(!payDeck.getClientsQueue().contains(client)) {
            return;
        }

        try {
            long servingTime = client.getTicketsToBuy() * 1000L;
            ServiceEvent serviceEvent = new ServiceEvent(payDeck.getId(), servingTime);
            serveClientService.sendServiceEvent(serviceEvent);

            Thread.sleep(servingTime);

            payDeck.getClientsQueue().remove(client);
            //New Serving Ended event
            serveClientService.sendEndedServicingEvent(serviceEvent);
        } catch (InterruptedException e) {
            System.out.println("Task interrupted for PayDeck: " + payDeck.getId());
            payDeck.crash();
            client.interrupt();
            CrashPaydeckEvent crashPaydeckEvent = new CrashPaydeckEvent(payDeck, client, LocalDateTime.now());
            serveClientService.sendInterruptedServing(crashPaydeckEvent);
        }
    }
}
