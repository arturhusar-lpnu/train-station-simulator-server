package event_listeners.web;

import events.Event;
import events.ServiceEvent;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class ServiceWebListener extends WebListener{
    public ServiceWebListener(SimpMessagingTemplate messagingTemplate) {
        super(messagingTemplate);
    }
    @Override
    public void update(Event event) {
        if(event instanceof ServiceEvent) {
            messagingTemplate.convertAndSend("/topic/pay-decks/service", event);
        }
    }
}
