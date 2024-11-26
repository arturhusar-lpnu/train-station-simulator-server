package events;

import generators.TicketSystem;

import java.time.LocalDateTime;

public class EndSystemEvent implements Event {
    private TicketSystem system;
    private final LocalDateTime endTime;
    public EndSystemEvent(LocalDateTime endTime) {
      //  this.system = system;
        this.endTime = endTime;
    }

    @Override
    public String convert() {
        return "System ended: " + endTime.toString();
    }
}