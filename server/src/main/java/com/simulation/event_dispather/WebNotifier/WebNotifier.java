package com.simulation.event_dispather.WebNotifier;

import com.simulation.event_dispather.EventObserver;
import com.simulation.event_listeners.web.WebListener;
import com.simulation.events.Event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.simulation.event_listeners.EventListener;

public class WebNotifier implements EventObserver {
    private final Map<String, List<WebListener>> listeners;

    public WebNotifier() {
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

        if (listener instanceof WebListener) {
            listeners.get(event.getType()).add((WebListener) listener);
        } else {
            throw new IllegalArgumentException("Listener must be of type WebListener");
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

        if (!(listener instanceof WebListener)) {
            throw new IllegalArgumentException("Listener must be of type WebListener");
        }

        List<WebListener> eventListeners = listeners.get(event.getType());
        if (eventListeners != null) {
            eventListeners.remove(listener);
        }
    }

    @Override
    public void notify(Event event) {
        if (event == null) {
            throw new IllegalArgumentException("Invalid event passed");
        }

        List<WebListener> eventListeners = this.listeners.get(event.getType());
        if(eventListeners != null) {
            for(WebListener listener : eventListeners) {
                listener.update(event);
            }
        }
    }
}
