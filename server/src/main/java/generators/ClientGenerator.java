package generators;

import lombok.Setter;
import models.Client;
import models.Position;
import strategy.tickets.FixedTicketsStrategy;
import strategy.tickets.TicketsGenerationStrategy;
import strategy.time.FixedTimeStrategy;
import strategy.time.TimeGenerationStrategy;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

// delay поставив в секундах, подумайте, чи норм, чи забрати і в мілісекундах]

// baawls nibber
// baaaaaaaaaaaawls
//
@Setter
public class ClientGenerator {
    private List<Client> clients;
    private TimeGenerationStrategy timeGenerationStrategy;
    private PrivilegeGenerator privilegeGenerator;
    private TicketsGenerationStrategy ticketsGenerationStrategy;
    private AtomicInteger clientCounter = new AtomicInteger(0);
    private ScheduledThreadPoolExecutor scheduler;

    public ClientGenerator(TimeGenerationStrategy timeGen, PrivilegeGenerator privilegeGen, TicketsGenerationStrategy ticketsGen) {
        this.timeGenerationStrategy = timeGen;
        this.privilegeGenerator = privilegeGen;
        this.ticketsGenerationStrategy = ticketsGen;
    }

    private void generateClient() {
        var moveSystem = TicketSystem.getInstance().getClientMoveSystem();
        var paydecks = TicketSystem.getInstance().getPayDeckSystem().getPayDecks();
        var entries = TicketSystem.getInstance().getRoomMap().getEntries();
        Position startPosition = entries.get(new Random().nextInt(0, entries.size()));
        Client client = new Client(clientCounter.incrementAndGet(),
                ticketsGenerationStrategy.getTickets(),
                startPosition,
                privilegeGenerator.getPrivilege());

        moveSystem.addClient(paydecks, client);

        scheduler.schedule(this::generateClient,
                timeGenerationStrategy.getNextGenerationDelay(),
                TimeUnit.SECONDS);
    };

    public void startGenerateClients() {
        scheduler = new ScheduledThreadPoolExecutor(1);

        scheduler.schedule(this::generateClient,
                timeGenerationStrategy.getNextGenerationDelay(),
                TimeUnit.SECONDS);
    }

    public void stopGenerateClients() {
        scheduler.shutdownNow();
    }
}
