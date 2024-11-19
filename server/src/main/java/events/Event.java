package events;


import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public abstract class Event {
    protected final LocalDateTime timestamp;

    public Event() {
        this.timestamp = LocalDateTime.now();
    }
}
