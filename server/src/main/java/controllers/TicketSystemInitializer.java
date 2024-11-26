package controllers;

import event_listeners.web.ClientCreationService;
import event_listeners.web.ServeClientService;
import generators.TicketSystem;
import generators.TicketSystemConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketSystemInitializer {

    private final ServeClientService serveClientService;
    private final ClientCreationService clientCreationService;
    @Autowired
    public TicketSystemInitializer(ServeClientService serveClientService, ClientCreationService clientCreationService) {
        this.serveClientService = serveClientService;
        this.clientCreationService = clientCreationService;
    }
    public TicketSystem initializeTicketSystem(TicketSystemConfig config) {
        // If you want to ensure the service is set
        config.getPayDeckSystem().setServeClientService(serveClientService);
        config.getClientGenerator().setClientCreationService(clientCreationService);
  //      config.getPayDeckSystem().setServeClientService(serveClientService);
        return TicketSystem.getInstance(config);
    }
}
