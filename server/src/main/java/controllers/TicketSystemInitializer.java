package controllers;

import services.ClientCreationService;
import services.ServeClientService;
import services.SimulationService;
import generators.TicketSystem;
import generators.TicketSystemConfig;
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
