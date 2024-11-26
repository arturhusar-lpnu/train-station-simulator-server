package controllers;

import generators.PayDeckSystem;
import generators.TicketSystem;
import generators.TicketSystemConfig;
import models.PayDeck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class SimulatorController {
    private final TicketSystemInitializer ticketSystemInitializer;

    @Autowired
    public SimulatorController(TicketSystemInitializer ticketSystemInitializer) {
        this.ticketSystemInitializer = ticketSystemInitializer;
    }

    private TicketSystem ticketSystem;
    @MessageMapping("/start-simulation")
    public void startSimulation(@Payload final TicketSystemConfig config) {
        System.out.println("Received start-simulation message with config: " + config);
        ticketSystem = ticketSystemInitializer.initializeTicketSystem(config);
        //ticketSystem = TicketSystem.getInstance(config);
        ticketSystem.startSystem();
    }

    @MessageMapping("/client-reached-deck")
    public void onClientReachedDeck(@Payload int paydeckId, @Payload int clientId) {
        PayDeckSystem pds = ticketSystem.getPayDeckSystem();
        //ticketSystem.getClientGenerator().getCLient(clientId);
        //pds.paydeckServeClient(paydeckId, clientId);
    }

    @MessageMapping("/end-simulation")
    public void endSimulation() {
        System.out.println("Received end-simulation message");
        ticketSystem.stopSystem();
    }
}
