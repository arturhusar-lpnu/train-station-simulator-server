package event_dispather.EventLogger;

import event_dispather.EventObserver;
import event_listeners.EventListener;
import event_listeners.log.LogListener;
import events.Event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventLogger implements EventObserver {
    private final Map<String, List<LogListener>> listeners;

    public EventLogger() {
        listeners = new HashMap<>();
    }

    @Override
    public void subscribe(Event event, EventListener listener) {
        if (event == null) {
            throw new IllegalArgumentException("Invalid even");
        }
        if(listener == null) {
            throw new IllegalArgumentException("Invalid listener");
        }
        listeners.putIfAbsent(event.getType(), new ArrayList<>());

        if (listener instanceof LogListener) {
            listeners.get(event.getType()).add((LogListener) listener);
        } else {
            throw new IllegalArgumentException("Listener must be of type LogListener");
        }
    }

    @Override
    public void unsubscribe(Event event, EventListener listener) {
        if (event == null) {
            throw new IllegalArgumentException("Invalid even");
        }
        if(listener == null) {
            throw new IllegalArgumentException("Invalid listener");
        }

        if (!(listener instanceof LogListener)) {
            throw new IllegalArgumentException("Listener must be of type LogListener");
        }

        List<LogListener> eventListeners = listeners.get(event.getType());
        if (eventListeners != null) {
            eventListeners.remove(listener);
        }
    }

    @Override
    public void notify(Event event) {
        if (event == null) {
            throw new IllegalArgumentException("Invalid event passed");
        }

        List<LogListener> eventListeners = this.listeners.get(event.getType());
        if(eventListeners != null) {
            for(LogListener listener : eventListeners) {
                listener.update(event);
            }
        }
    }
}
