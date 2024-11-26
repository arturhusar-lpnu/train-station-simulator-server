package controllers;

import generators.PayDeckSystem;
import generators.TicketSystem;
import generators.TicketSystemConfig;
import models.PayDeck;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class SimulatorController {
    private TicketSystem ticketSystem;
    @MessageMapping("/start-simulation")
    public void startSimulation(@Payload final TicketSystemConfig config) {
        System.out.println("Received start-simulation message with config: " + config);

        ticketSystem = TicketSystem.getInstance(config);
        ticketSystem.startSystem();
    }

    @MessageMapping("/client-reached-deck")
    public void onClientReachedDeck(@Payload final PayDeck paydeck) {
        PayDeckSystem pds = ticketSystem.getPayDeckSystem();
        pds.paydeckServeClient(paydeck);
    }

    @MessageMapping("/end-simulation")
    public void endSimulation() {
        System.out.println("Received end-simulation message");
        ticketSystem.stopSystem();
    }
}
