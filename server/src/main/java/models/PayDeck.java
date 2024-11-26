package models;

import event_dispather.WebNotifier.WebNotifier;
import events.ServiceEvent;
import lombok.Getter;
import lombok.Setter;

import java.util.PriorityQueue;
import java.util.concurrent.atomic.AtomicBoolean;


//================================================================================
// PayDeck that serves a client queue based on
// the privileges priority of each client
//================================================================================

@Getter
@Setter
public class PayDeck {
    private final int id;
    private final PriorityQueue<Client> clientsQueue;
    private AtomicBoolean isWorking;
    private WebNotifier webNotifier;

    public PayDeck(int id, WebNotifier webNotifier) {
        this.id = id;
        isWorking = new AtomicBoolean(true);
        clientsQueue = new PriorityQueue<>( (c1, c2) -> Integer.compare(calculatePriority(c1), calculatePriority(c2)));
        this.webNotifier = webNotifier;
    }
    //================================================================================
    // Допоміжна може можна скоротити
    //================================================================================
    private int calculatePriority(Client c) {
       return 0; //c.getPrivileges().stream().mapToInt(PrivilegeCategory::getPriority).sum();
    }

    public void crash() {
        isWorking.set(false);
    }
    //================================================================================
    // thread main task
    //================================================================================

    // Added clients after it is generated and picked a Deck based on Availability якось так
    public void addClient(Client client) {
        //Throw event
        synchronized (clientsQueue) {
            clientsQueue.add(client);
            //webNotifier.notify(new QueueChangedEvent(this));
            //System.out.println("Client added to paydesk queue: " + client);
        }
    }
}
