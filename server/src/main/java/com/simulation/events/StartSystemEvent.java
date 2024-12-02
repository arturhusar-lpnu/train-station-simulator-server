package com.simulation.events;

import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Getter
public class StartSystemEvent implements Event {
    private final List<String> payDeckIds;
    private final LocalDateTime startTime;
    private final String reservedPayDackId;

    public StartSystemEvent(LocalDateTime startTime, List<String> payDeckIds, String reservedPayDackId) {
        this.payDeckIds = payDeckIds;
        this.startTime = startTime;
        this.reservedPayDackId = reservedPayDackId;
    }

    @Override
    public String convert() {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String currentTime = startTime.format(timeFormatter);
        return "System started: [" + currentTime + "]";
    }
}