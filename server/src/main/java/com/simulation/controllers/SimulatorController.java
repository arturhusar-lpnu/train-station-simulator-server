package com.simulation.controllers;

import com.simulation.dtos.ConfigDto;
import com.simulation.exceptions.InvalidArgumentException;
import com.simulation.generators.PayDeckSystem;
import com.simulation.generators.TicketSystem;
import com.simulation.generators.TicketSystemConfig;
import com.simulation.models.Client;
import com.simulation.models.WorkingTimer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;

@Controller
public class SimulatorController {
    private final TicketSystemInitializer ticketSystemInitializer;
    private TicketSystem ticketSystem;
    private WorkingTimer workingTimer;

    @Autowired
    public SimulatorController(TicketSystemInitializer ticketSystemInitializer) {
        this.ticketSystemInitializer = ticketSystemInitializer;
    }

    @MessageMapping("/start-simulation")
    public void startSimulation(@Payload final ConfigDto config) throws InvalidArgumentException {
        System.out.println("Received start-simulation message with config: " + config);

        TicketSystemConfig ticketSystemConfig = new TicketSystemConfig(config);

        ticketSystem = ticketSystemInitializer.initializeTicketSystem(ticketSystemConfig);
        workingTimer = new WorkingTimer(ticketSystemConfig.getDurationOfDay(), ticketSystem);
        workingTimer.startTimer();//Запускає систему як вичерпається час зупинить
    }

    @MessageMapping("/client-reached-deck")
    public void onClientReachedDeck(@Payload String paydeckId, @Payload String clientId) {
        PayDeckSystem pds = ticketSystem.getPayDeckSystem();
        Client client = ticketSystem.getClientGenerator().getClientByID(clientId);
        pds.paydeckServeClient(paydeckId, client);
    }

    @MessageMapping("/end-simulation")
    public void endSimulation() {
        System.out.println("Received end-simulation message");
        workingTimer.stopTimer();
    }
}
