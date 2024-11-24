package event_listeners.log;

import events.Event;

import java.io.FileWriter;
import java.io.IOException;

public class CrashPaydeckLogListener extends LogListener{
    public CrashPaydeckLogListener (String fileName) { super(fileName); }

    @Override
    public void update(Event event) {
        try (FileWriter writer = new FileWriter(super.log, true)) {
            writer.write(event.convert() + System.lineSeparator());
        } catch (IOException e) {
            System.err.println("Error writing to log file: " + e.getMessage());
        }
    }
}
