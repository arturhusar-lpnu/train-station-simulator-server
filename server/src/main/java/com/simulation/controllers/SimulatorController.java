package com.simulation.controllers;

import com.simulation.dtos.ClientReachedDeckDto;
import com.simulation.dtos.ConfigDto;
import com.simulation.exceptions.InvalidArgumentException;
import com.simulation.generators.PayDeckSystem;
import com.simulation.config.TicketSystemConfig;
import com.simulation.models.Client;
import com.simulation.services.SimulationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;

@Controller
public class SimulatorController {
    private final SimulationService simulation;

    @Autowired
    public SimulatorController(SimulationService simulation) {
        this.simulation = simulation;
    }

    @MessageMapping("/start-simulation")
    public void startSimulation(@Payload final ConfigDto config) throws InvalidArgumentException {
        System.out.println("Received start-simulation message with config: " + config);

        TicketSystemConfig ticketSystemConfig = new TicketSystemConfig(config);
        simulation.startSimulation(ticketSystemConfig);
    }

    @MessageMapping("/client-reached-deck")
    public void onClientReachedDeck(@Payload ClientReachedDeckDto reachedDeck) {
        String payDeckId = reachedDeck.payDeckId();
        String clientId = reachedDeck.clientId();
        System.out.println(payDeckId + " " + clientId);
        PayDeckSystem pds = simulation.getTicketSystem().getPayDeckSystem();
        Client client = simulation.getTicketSystem().getClientGenerator().getClientByID(clientId);

        pds.payDeckServeClient(payDeckId, client);
    }

    @MessageMapping("/end-simulation")
    public void endSimulation() {
        System.out.println("Received end-simulation message");
        simulation.stopSimulation();
    }
}
