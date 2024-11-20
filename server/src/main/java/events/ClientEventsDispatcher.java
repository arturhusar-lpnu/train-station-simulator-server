package events;

import java.util.ArrayList;
import java.util.List;

public class ClientEventsDispatcher {
    private final List<ClientEventListener> listeners = new ArrayList<>();

    public void addListener(final ClientEventListener listener) {
        listeners.add(listener);
    }

    public void removeListener(final ClientEventListener listener) {
        listeners.remove(listener);
    }


}
