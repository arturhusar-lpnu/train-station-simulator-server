package com.simulation.event_listeners.web;

import com.simulation.events.CreationEvent;
import com.simulation.events.Event;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class ClientCreatedWebListener extends WebListener {
    public ClientCreatedWebListener(SimpMessagingTemplate messagingTemplate) {
        super(messagingTemplate);
    }
    //private EventMapper clientEventMapper;
    @Override
    public void update(Event event) {
        if(event instanceof CreationEvent) {
            //ClientDTO dto = clientEventMapper.Map<ClientDTO>(event);
            //messagingTemplate.convertAndSend("/topic/clients", dto);
            messagingTemplate.convertAndSend("/topic/clients", event);
        }
    }
}
