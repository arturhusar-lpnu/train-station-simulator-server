package generators;

import lombok.Setter;
import models.Client;
import strategy.tickets.FixedTicketsStrategy;
import strategy.tickets.TicketsGenerationStrategy;
import strategy.time.FixedTimeStrategy;
import strategy.time.TimeGenerationStrategy;


@Setter
public class ClientGenerator {
    private TimeGenerationStrategy timeGenerationStrategy;
    private PrivilegeGenerator privilegeGenerator;
    private TicketsGenerationStrategy ticketsGenerationStrategy;
    private Thread currentThread;
    private volatile boolean running = true;

    public ClientGenerator(TimeGenerationStrategy timeGen, PrivilegeGenerator privilegeGen, TicketsGenerationStrategy ticketsGen) {
        this.timeGenerationStrategy = timeGen;
        this.privilegeGenerator = privilegeGen;
        this.ticketsGenerationStrategy = ticketsGen;

        currentThread = new Thread(this::generateClients);
    }
    public ClientGenerator() {
        this.timeGenerationStrategy = new FixedTimeStrategy(1);
        this.privilegeGenerator = new PrivilegeGenerator();
        this.ticketsGenerationStrategy = new FixedTicketsStrategy(2);

        this.currentThread = new Thread(this::generateClients);
    }

    public void generateClients() {
        while (running) {
            try {
                long delay = timeGenerationStrategy.getNextGenerationDelay();
                Thread.sleep(delay);
                Client newClient =  getClient();
                //throw Event
            } catch (InterruptedException e) {
                running = false;
                break;
            }
        }
    }

    public Client getClient() {
        return null;
    };

    public void start() {
        if (currentThread == null || !currentThread.isAlive()) {
            running = true;
            currentThread = new Thread(this::generateClients);
            currentThread.start();
        }
    }

    public void stop() {
        running = false;
        if(currentThread != null) {
            currentThread.interrupt();
        }
    }
}
