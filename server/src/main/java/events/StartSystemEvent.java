package events;

import generators.TicketSystem;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class StartSystemEvent implements Event {
    public TicketSystem system;

    public StartSystemEvent(TicketSystem system) {
        this.system = system;
    }

    @Override
    public String convert() {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String currentTime = LocalTime.now().format(timeFormatter);
        return "System started: [" + currentTime + "]" + ": " + system.toString();
    }
}
