package com.simulation.event_listeners.web;

import com.simulation.events.Event;
import com.simulation.events.RecoveryPaydeckEvent;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class DeckRecoveredWebListener extends WebListener {
    public DeckRecoveredWebListener(SimpMessagingTemplate messagingTemplate) {
        super(messagingTemplate);
    }

    @Override
    public void update(Event event) {
        if(event instanceof RecoveryPaydeckEvent)
        {
            messagingTemplate.convertAndSend("/topic/pay-decks/recovered", event);
        }
    }
}
