package event_dispather;

import events.Event;

public interface EventObserver {
    void subscribe(String eventType, EventListener listener);
    void unsubscribe(String eventType, EventListener listener);
    void notify(Event event);
}
