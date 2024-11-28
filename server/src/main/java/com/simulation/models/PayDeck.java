package com.simulation.models;

import com.simulation.services.ServeClientService;
import com.simulation.events.ModifiedQueueEvent;
import lombok.Getter;
import lombok.Setter;

import java.util.PriorityQueue;
import java.util.UUID;


//================================================================================
// PayDeck that serves a client queue based on
// the privileges priority of each client
//================================================================================

@Getter
@Setter
public class PayDeck {
    private final String id;
    private final PriorityQueue<Client> clientsQueue;
    private boolean isWorking;
    private ServeClientService serveClientService;

    public PayDeck() {
        this.id = UUID.randomUUID().toString();
        isWorking = true;
        clientsQueue = new PriorityQueue<>( (c1, c2) -> c2.getPrivilege().compareTo(c1.getPrivilege()));
    }
    public void setService(ServeClientService serveClientService) {
        this.serveClientService = serveClientService;
    }

    public void addClient(Client client) {
        clientsQueue.offer(client);
        ModifiedQueueEvent modifiedQueueEvent = new ModifiedQueueEvent(this);
        serveClientService.sendModifiedQueueEvent(modifiedQueueEvent);
    }
}
