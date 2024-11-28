package com.simulation.models;

import com.simulation.services.ServeClientService;
import com.simulation.events.CrashPaydeckEvent;
import com.simulation.events.ServiceEvent;

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
            return; //Add event later
        }

        try {
            long servingTime = client.getTicketsToBuy() * 3000L;
            ServiceEvent serviceStartedEvent = new ServiceEvent(payDeck.getId(), servingTime, LocalDateTime.now());
            serveClientService.sendServiceEvent(serviceStartedEvent);

            Thread.sleep(servingTime);

            payDeck.getClientsQueue().remove(client);
            ServiceEvent serviceEndedEvent = new ServiceEvent(payDeck.getId(), servingTime, LocalDateTime.now());
            //New Serving Ended event
            serveClientService.sendEndedServicingEvent(serviceEndedEvent);
        } catch (InterruptedException e) {
            System.out.println("Task interrupted for PayDeck: " + payDeck.getId());
            client.interrupt();
        }
    }
}
