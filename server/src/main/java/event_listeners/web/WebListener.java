package event_listeners.web;

import event_listeners.EventListener;
import events.Event;
import org.springframework.messaging.simp.SimpMessagingTemplate;

public abstract class WebListener implements EventListener {
    protected final SimpMessagingTemplate messagingTemplate;

    public WebListener(final SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @Override
    public void update(Event event) {
        messagingTemplate.convertAndSend("/topic/event", event);
    }
}
