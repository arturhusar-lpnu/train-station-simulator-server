package controllers;

import event_listeners.web.ServeClientService;
import generators.TicketSystem;
import generators.TicketSystemConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketSystemInitializer {

    ServeClientService serveClientService;
    @Autowired
    public TicketSystemInitializer(ServeClientService serveClientService) {
        this.serveClientService = serveClientService;
    }
    public TicketSystem initializeTicketSystem(TicketSystemConfig config) {
        // If you want to ensure the service is set
        config.getPayDeckSystem().setServeClientService(serveClientService);
  //      config.getPayDeckSystem().setServeClientService(serveClientService);
        return TicketSystem.getInstance(config);
    }
}
