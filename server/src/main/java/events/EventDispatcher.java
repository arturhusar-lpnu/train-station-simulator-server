package events;

import events.EventListener;
import java.util.ArrayList;
import java.util.List;

public class EventDispatcher {
    private final List<EventListener> listeners = new ArrayList<>();

    public void addEventListener(EventListener listener) {
        listeners.add(listener);
    }

    public void removeEventListener(EventListener listener) {
        listeners.remove(listener);
    }
    public void dispatchEvent(Event event) {
        for (EventListener listener : listeners) {
            listener.handleEvent(event);
        }
    }
}
