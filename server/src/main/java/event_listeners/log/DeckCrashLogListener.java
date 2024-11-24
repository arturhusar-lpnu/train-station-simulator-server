package event_listeners.log;

import events.CrashPaydeckEvent;
import events.Event;

import java.io.FileWriter;
import java.io.IOException;

public class DeckCrashLogListener extends LogListener{
    public DeckCrashLogListener (String fileName) { super(fileName); }

    @Override
    public void update(Event event) {
        if(event instanceof CrashPaydeckEvent) {
            try (FileWriter writer = new FileWriter(super.log, true)) {
                writer.write(event.convert() + System.lineSeparator());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
