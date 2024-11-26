package generators;

import services.ClientCreationService;
import events.CreationEvent;
import lombok.Setter;
import models.Client;
import models.PayDeck;
import strategy.tickets.TicketsGenerationStrategy;
import strategy.time.TimeGenerationStrategy;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

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
    private ScheduledThreadPoolExecutor scheduler;

    private ClientCreationService clientCreationService;
    public ClientGenerator(TimeGenerationStrategy timeGen, PrivilegeGenerator privilegeGen, TicketsGenerationStrategy ticketsGen) {
        this.timeGenerationStrategy = timeGen;
        this.privilegeGenerator = privilegeGen;
        this.ticketsGenerationStrategy = ticketsGen;
    }

    public void setClientService(ClientCreationService clientCreationService) {
        this.clientCreationService = clientCreationService;
    }

    private void generateClient() {
        var ticketSystem = TicketSystem.getInstance();
        var payDecks = ticketSystem.getPayDeckSystem().getPayDecks();
        if(clients.size() >= payDecks.size()) {
            return;
        }

        String newClientId = UUID.randomUUID().toString();

        Client client = new Client(
                newClientId,
                ticketsGenerationStrategy.getTickets(),
                privilegeGenerator.getPrivilege()
        );

        clients.add(client);
        PayDeck selectedPayDeck = PayDeckChooseSystem.choosePaydeck(payDecks, client);

        CreationEvent creationEvent = new CreationEvent(client, LocalDateTime.now(), selectedPayDeck);
        clientCreationService.sendClientCreatedEvent(creationEvent);

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
    public Client getClientByID(String clientId) {
        return clients.stream().filter(c -> c.getId().equals(clientId)).findFirst().orElse(null);
    }
}
