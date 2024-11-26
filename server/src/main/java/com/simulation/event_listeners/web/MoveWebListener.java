package com.simulation.event_listeners.web;

import com.simulation.events.Event;
import com.simulation.events.MoveEvent;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class MoveWebListener extends WebListener {
    public MoveWebListener(SimpMessagingTemplate messagingTemplate) {
        super(messagingTemplate);
    }
    @Override
    public void update(Event event) {
        if(event instanceof MoveEvent) {
            messagingTemplate.convertAndSend("/topic/clients/move", event);
        }
    }
}
