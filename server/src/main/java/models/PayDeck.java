package models;

import lombok.Getter;
import lombok.Setter;
import models.privileges.PrivilegeCategory;
import models.privileges.decorators.Interrupted;

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
    private  AtomicBoolean isAvailable;
    private Thread currentThread;

    public PayDeck(int id) {
        this.id = id;
        isWorking = new AtomicBoolean(true);
        isAvailable = new AtomicBoolean(false);
        clientsQueue = new PriorityQueue<>( (c1, c2) -> Integer.compare(calculatePriority(c1), calculatePriority(c2)));
        currentThread = new Thread(this::serveClient);
        currentThread.start();
    }
    //================================================================================
    // Допоміжна може можна скоротити
    //================================================================================
    private int calculatePriority(Client c) {
        return c.getPrivileges().stream().mapToInt(PrivilegeCategory::getPriority).sum();
    }

    //================================================================================
    // thread main task
    //================================================================================
    public void serveClient() {
        if(!isAvailable.get()) {
            System.out.println("Busy serving client");
            return;
        }
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

            isAvailable.set(false);
            Thread.sleep(ticketsToAdd * 1000); // simulate tickets creation
        } catch (InterruptedException e) {
            e.printStackTrace();
            //If a pay deck is broken client should have the highest priority хз рома таке написав як то зробити чесним я хз
            PrivilegeCategory clientCategory = client.getPrivileges().stream().findFirst().orElse(null); // Check later
            client.addPrivilegeCategory(new Interrupted(clientCategory));
        }
    }

    //Triggers on an event
    public void breakDeck() {
        this.stop(); // to interrupt the thread
    }

    // Added clients after it is generated and picked a Deck based on Availability якось так
    public void addClient(Client client) {
        synchronized (clientsQueue) {
            clientsQueue.add(client);
            System.out.println("Client added to paydesk queue: " + client);
        }
    }

    //start the tread again if was interrupted or on event крч ти поняв
    public void start() {
        if (currentThread == null || !currentThread.isAlive()) {
            isWorking.set(true);
            currentThread = new Thread(this::serveClient);
            currentThread.start();
        }
    }

    public void stop() {
        isWorking.set(false);
        currentThread.interrupt();
    }
}
