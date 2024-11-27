package com.simulation.event_dispather;

import com.simulation.events.Event;
import com.simulation.event_listeners.EventListener;

public interface EventObserver {
    void subscribe(Event event, EventListener listener);
    void unsubscribe(Event event, EventListener listener);
    void notify(Event event);
}
