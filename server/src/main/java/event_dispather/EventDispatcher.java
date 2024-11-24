package event_dispather;

import events.Event;

import java.util.List;
import event_listeners.EventListener;

public class EventDispatcher implements EventObserver {
    private List<EventObserver> observers;

    void addObserver(EventObserver observer) {
        observers.add(observer);
    }
    void removeObserver(EventObserver observer) { observers.remove(observer); }
    @Override
    public void subscribe(String eventType, EventListener listener) {
        observers.forEach(o -> o.subscribe(eventType, listener));
    }

    @Override
    public void unsubscribe(String eventType, EventListener listener) {
        observers.forEach(o -> o.unsubscribe(eventType, listener));
    }

    @Override
    public void notify(Event event) {
        observers.forEach(o -> o.notify(event));
    }
}
