package com.simulation.services;

import com.simulation.models.TicketSystem;
import com.simulation.config.TicketSystemConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketSystemInitializer {

    private final ServeClientService serveClientService;
    private final ClientService clientService;
    private final SimulationEventsService simulationEventsService;
    @Autowired
    public TicketSystemInitializer(ServeClientService serveClientService,
                                   ClientService clientService,
                                   SimulationEventsService simulationEventsService) {
        this.serveClientService = serveClientService;
        this.clientService = clientService;
        this.simulationEventsService = simulationEventsService;
    }
    public TicketSystem initializeTicketSystem(TicketSystemConfig config) {

        config.getPayDeckSystem().setServeClientService(serveClientService);
        config.getClientGenerator().setClientService(clientService);

        TicketSystem ts = TicketSystem.getInstance(config);
        ts.setSimulationEventsService(simulationEventsService);

        return ts;
    }
}