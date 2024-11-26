package generators;

import event_listeners.web.ClientCreationService;
import events.CreationEvent;
import lombok.Setter;
import models.Client;
import models.PayDeck;
import models.Position;
import strategy.tickets.FixedTicketsStrategy;
import strategy.tickets.TicketsGenerationStrategy;
import strategy.time.FixedTimeStrategy;
import strategy.time.TimeGenerationStrategy;

import java.util.ArrayList;
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

    private ClientCreationService clientCreationService;
    public ClientGenerator(TimeGenerationStrategy timeGen, PrivilegeGenerator privilegeGen, TicketsGenerationStrategy ticketsGen, ClientCreationService clientCreationService) {
        this.timeGenerationStrategy = timeGen;
        this.privilegeGenerator = privilegeGen;
        this.ticketsGenerationStrategy = ticketsGen;
        this.clientCreationService = clientCreationService;
    }

    private void generateClient() {
        var ticketSystem = TicketSystem.getInstance();
        var moveSystem = ticketSystem.getClientMoveSystem();
        var paydecks = ticketSystem.getPayDeckSystem().getPayDecks();
        var entries = ticketSystem.getRoomMap().getEntries();

        Position startPosition = entries.get(new Random().nextInt(0, entries.size()));
        Client client = new Client(
                clientCounter.incrementAndGet(),
                ticketsGenerationStrategy.getTickets(),
                startPosition,
                privilegeGenerator.getPrivilege()
        );


        clients.add(client);
        PayDeck selectedPayDeck = PayDeckChooseSystem.choosePaydeck(paydecks, client);

        //CreationEvent creationEvent = new CreationEvent(client);
        //clientCreationService.sendClientCreatedEvent();

        scheduler.schedule(this::generateClient,
                timeGenerationStrategy.getNextGenerationDelay(),
                TimeUnit.SECONDS);
    }

    public void startGenerateClients() {
        scheduler = new ScheduledThreadPoolExecutor(1);

        scheduler.schedule(this::generateClient,
                timeGenerationStrategy.getNextGenerationDelay(),
                TimeUnit.SECONDS);
    }

    public void stopGenerateClients() {
        scheduler.shutdownNow();
    }

    public List<Client> getClients() {
        return new ArrayList<>(clients);
    }
}
