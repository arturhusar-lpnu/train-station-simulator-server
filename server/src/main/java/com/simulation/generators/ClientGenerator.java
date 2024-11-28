package com.simulation.generators;

import com.simulation.models.TicketSystem;
import com.simulation.services.ClientService;
import com.simulation.events.CreationEvent;
import lombok.Setter;
import com.simulation.models.Client;
import com.simulation.models.PayDeck;
import com.simulation.strategy.tickets.TicketsGenerationStrategy;
import com.simulation.strategy.time.TimeGenerationStrategy;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

// delay поставив в секундах, подумайте, чи норм, чи забрати і в мілісекундах]

@Setter
public class ClientGenerator {
    private List<Client> clients;
    private TimeGenerationStrategy timeGenerationStrategy;
    private PrivilegeGenerator privilegeGenerator;
    private TicketsGenerationStrategy ticketsGenerationStrategy;
    private ScheduledThreadPoolExecutor scheduler;

    @Setter
    private ClientService clientService;
    public ClientGenerator(TimeGenerationStrategy timeGen, PrivilegeGenerator privilegeGen, TicketsGenerationStrategy ticketsGen) {
        this.timeGenerationStrategy = timeGen;
        this.privilegeGenerator = privilegeGen;
        this.ticketsGenerationStrategy = ticketsGen;
        this.clients = new ArrayList<>();
    }

    private void generateClient() {
        var ticketSystem = TicketSystem.getInstance();
        var payDecks = ticketSystem.getPayDeckSystem().getPayDecks();
        System.out.println("Generating clients");
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
        System.out.println("Client Added");
        PayDeck selectedPayDeck = PayDeckChooseSystem.choosePaydeck(payDecks, client);

        CreationEvent creationEvent = new CreationEvent(client, LocalDateTime.now(), selectedPayDeck);
        clientService.sendClientCreatedEvent(creationEvent);

        scheduler.schedule(this::generateClient,
                timeGenerationStrategy.getNextGenerationDelay(),
                TimeUnit.SECONDS);
    }

    public void startGenerateClients() {
        scheduler = new ScheduledThreadPoolExecutor(1);
        System.out.println("Scheduled");
        scheduler.schedule(this::generateClient,
                timeGenerationStrategy.getNextGenerationDelay(),
                TimeUnit.SECONDS);
    }

    public void stopGenerateClients() {
        scheduler.shutdownNow();
    }

    public Client getClientByID(String clientId) {
        return clients.stream().filter(c -> c.getId().equals(clientId)).findFirst().orElse(null);
    }
}
