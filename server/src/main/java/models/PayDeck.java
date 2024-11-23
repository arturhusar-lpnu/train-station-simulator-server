package models;

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
public class PayDeck implements Runnable {
    private final int id;
    private final PriorityQueue<Client> clientsQueue;
    private AtomicBoolean isWorking;

    public PayDeck(int id) {
        this.id = id;
        isWorking = new AtomicBoolean(true);
        clientsQueue = new PriorityQueue<>( (c1, c2) -> Integer.compare(calculatePriority(c1), calculatePriority(c2)));
    }
    //================================================================================
    // Допоміжна може можна скоротити
    //================================================================================
    private int calculatePriority(Client c) {
       return 0; //c.getPrivileges().stream().mapToInt(PrivilegeCategory::getPriority).sum();
    }
    public void run() {

    }

    public void crash() {
        isWorking.set(false);
    }
    //================================================================================
    // thread main task
    //================================================================================
    public void serveClient() {
        if(!isWorking.get()) {
            System.out.println("Not working now");
            return;
        }
        Client client = clientsQueue.poll();
        System.out.println("serving client");

        try {
            int ticketsToAdd = client.getTicketsToBuy();

            if(ticketsToAdd < 0) {
                return;
            }

//            isAvailable.set(false);
            Thread.sleep(ticketsToAdd * 1000); // simulate tickets creation
        } catch (InterruptedException e) {
            e.printStackTrace();
            //If a pay deck is broken client should have the highest priority хз рома таке написав як то зробити чесним я хз
//            PrivilegeCategory clientCategory = client.getPrivileges().stream().findFirst().orElse(null); // Check later
//            client.addPrivilegeCategory(new Interrupted(clientCategory));
        }
    }
    // Added clients after it is generated and picked a Deck based on Availability якось так
    public void addClient(Client client) {
        synchronized (clientsQueue) {
            clientsQueue.add(client);
            System.out.println("Client added to paydesk queue: " + client);
        }
    }
}
