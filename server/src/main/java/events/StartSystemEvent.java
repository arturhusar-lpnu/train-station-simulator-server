package events;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class StartSystemEvent implements Event {
    private final LocalDateTime startTime;

    public StartSystemEvent(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    @Override
    public String convert() {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String currentTime = startTime.format(timeFormatter);
        return "System started: [" + currentTime + "]";
    }
}
