package event_dispather;

import events.Event;
import event_listeners.EventListener;

public interface EventObserver {
    void subscribe(Event event, EventListener listener);
    void unsubscribe(Event event, EventListener listener);
    void notify(Event event);
}
