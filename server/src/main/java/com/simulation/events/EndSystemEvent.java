package com.simulation.events;

import java.time.LocalDateTime;

public class EndSystemEvent implements Event {
    private final LocalDateTime endTime;
    public EndSystemEvent(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    @Override
    public String convert() {
        return "System ended: " + endTime.toString();
    }
}