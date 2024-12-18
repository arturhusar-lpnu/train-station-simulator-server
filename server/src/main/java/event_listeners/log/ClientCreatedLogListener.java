package event_listeners.log;

import events.CreationEvent;
import events.Event;

import java.io.FileWriter;
import java.io.IOException;

public class ClientCreatedLogListener extends LogListener{
    public ClientCreatedLogListener(String fileName) { super(fileName); }

    @Override
    public void update(Event event) {
        if(event instanceof CreationEvent) {
            try (FileWriter writer = new FileWriter(super.log, true)) {
                writer.write(event.convert() + System.lineSeparator());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}