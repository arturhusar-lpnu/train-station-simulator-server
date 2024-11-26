package models;

import services.ServeClientService;
import events.ModifiedQueueEvent;
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

    //================================================================================
    // Допоміжна може можна скоротити
    //================================================================================

    public void crash() {
        isWorking = false;
    }
    public void recover() {
        isWorking = true;
    }
    //================================================================================
    // thread main task
    //================================================================================

    public void addClient(Client client) {
        clientsQueue.offer(client);
        ModifiedQueueEvent modifiedQueueEvent = new ModifiedQueueEvent(this);
        serveClientService.sendModifiedQueueEvent(modifiedQueueEvent);
    }

    public int getQueueSize() {
        return clientsQueue.size();
    }
}
