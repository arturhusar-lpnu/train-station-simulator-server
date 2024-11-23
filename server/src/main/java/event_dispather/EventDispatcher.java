package event_dispather;

import events.Event;

import java.util.List;

public class EventDispatcher {
    private List<EventObserver> observers;

    void addObserver(EventObserver observer) {
        observers.add(observer);
    }
    void notifyAllObservers(Event event) {
        observers.forEach(o -> o.notify(event));
    }
}
