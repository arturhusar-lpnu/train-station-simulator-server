package com.simulation.event_listeners.web;

import com.simulation.events.CrashPaydeckEvent;
import com.simulation.events.Event;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class DeckCrashWebListener extends WebListener{

    public DeckCrashWebListener(SimpMessagingTemplate messagingTemplate) {
        super(messagingTemplate);
    }

    @Override
    public void update(Event event) {
        if(event instanceof CrashPaydeckEvent) {
            messagingTemplate.convertAndSend("/topic/pay-decks/crashed", event);
        }
    }
}
