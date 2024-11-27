package com.simulation.controllers;

import com.simulation.services.ClientCreationService;
import com.simulation.services.ServeClientService;
import com.simulation.services.SimulationService;
import com.simulation.generators.TicketSystem;
import com.simulation.generators.TicketSystemConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketSystemInitializer {

    private final ServeClientService serveClientService;
    private final ClientCreationService clientCreationService;
    private final SimulationService simulationService;
    @Autowired
    public TicketSystemInitializer(ServeClientService serveClientService,
                                   ClientCreationService clientCreationService,
                                   SimulationService simulationService) {
        this.serveClientService = serveClientService;
        this.clientCreationService = clientCreationService;
        this.simulationService = simulationService;
    }
    public TicketSystem initializeTicketSystem(TicketSystemConfig config) {
        // If you want to ensure the service is set
        config.getPayDeckSystem().setServeClientService(serveClientService);
        config.getClientGenerator().setClientCreationService(clientCreationService);
  //      config.getPayDeckSystem().setServeClientService(serveClientService);
        TicketSystem ts = TicketSystem.getInstance(config);
        ts.setSimulationService(simulationService);

        return ts;
    }
}
