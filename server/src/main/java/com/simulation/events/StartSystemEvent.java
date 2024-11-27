package com.simulation.events;

import com.simulation.models.PayDeck;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class StartSystemEvent implements Event {
    private final List<PayDeck> paydecks;
    private final LocalDateTime startTime;

    public StartSystemEvent(LocalDateTime startTime, List<PayDeck> paydecks) {
        this.paydecks = paydecks == null ? new ArrayList<>() : new ArrayList<>(paydecks);;
        this.startTime = startTime;
    }

    @Override
    public String convert() {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String currentTime = startTime.format(timeFormatter);
        return "System started: [" + currentTime + "]";
    }
}
