package event_dispather.WebNotifier;

import event_dispather.EventObserver;
import event_listeners.web.WebListener;
import events.Event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import event_listeners.EventListener;

public class WebNotifier implements EventObserver {
    private final Map<String, List<WebListener>> listeners;

    public WebNotifier() {
        listeners = new HashMap<>();
    }

    @Override
    public void subscribe(String eventType, EventListener listener) {
        if (eventType == null) {
           throw new IllegalArgumentException("Invalid evenType");
        }
        if(listener == null) {
            throw new IllegalArgumentException("Invalid listener");
        }
        listeners.putIfAbsent(eventType, new ArrayList<>());

        if (listener instanceof WebListener) {
            listeners.get(eventType).add((WebListener) listener);
        } else {
            throw new IllegalArgumentException("Listener must be of type WebListener");
        }
    }

    @Override
    public void unsubscribe(String eventType, EventListener listener) {
        if (eventType == null) {
            throw new IllegalArgumentException("Invalid evenType");
        }
        if(listener == null) {
            throw new IllegalArgumentException("Invalid listener");
        }

        if (!(listener instanceof WebListener)) {
            throw new IllegalArgumentException("Listener must be of type WebListener");
        }

        List<WebListener> eventListeners = listeners.get(eventType);
        if (eventListeners != null) {
            eventListeners.remove(listener);
        }
    }

    @Override
    public void notify(Event event) {
        if (event == null) {
            throw new IllegalArgumentException("Invalid event passed");
        }

        List<WebListener> eventListeners = this.listeners.get(event.convert());
        if(eventListeners != null) {
            for(WebListener listener : eventListeners) {
                listener.update(event);
            }
        }
    }
}
