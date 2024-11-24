package event_listeners.log;

import events.Event;
import events.RecoveryPaydeckEvent;

import java.io.FileWriter;
import java.io.IOException;

public class DeckRecoveredLogListener extends LogListener{
    public DeckRecoveredLogListener (String fileName) { super(fileName); }

    @Override
    public void update(Event event) {
        if(event instanceof RecoveryPaydeckEvent) {
            try (FileWriter writer = new FileWriter(super.log, true)) {
                writer.write(event.convert() + System.lineSeparator());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}