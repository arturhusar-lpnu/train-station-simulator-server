package event_listeners.web;

import dtos.ClientDTO;
import events.Event;
import models.Client;
import org.springframework.messaging.simp.SimpMessagingTemplate;

public class ClientCreatedWebListener extends WebListener {
    public ClientCreatedWebListener(SimpMessagingTemplate messagingTemplate) {
        super(messagingTemplate);
    }
    //private EventMapper clientEventMapper;
    @Override
    public void update(Event event) {
        //ClientDTO dto = clientEventMapper.Map<ClientDTO>(event);
        //messagingTemplate.convertAndSend("/topic/clients", dto);
        messagingTemplate.convertAndSend("/topic/clients", event);
    }
}
