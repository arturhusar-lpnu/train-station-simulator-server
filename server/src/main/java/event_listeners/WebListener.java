package event_listeners;

import events.Event;
import org.springframework.messaging.simp.SimpMessagingTemplate;

public abstract class WebListener implements EventListener {
    protected final SimpMessagingTemplate messagingTemplate;

    public WebListener(final SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void update(Event event) {
        messagingTemplate.convertAndSend("/topic/event", event);
    }
}
