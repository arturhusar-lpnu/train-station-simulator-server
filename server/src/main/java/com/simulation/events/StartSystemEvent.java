package com.simulation.events;

import com.simulation.models.PayDeck;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Getter
public class StartSystemEvent implements Event {
    private final List<String> payDeckIds;
    private final LocalDateTime startTime;

    public StartSystemEvent(LocalDateTime startTime, List<String> payDeckIds) {
        this.payDeckIds = payDeckIds;
        this.startTime = startTime;
    }

    @Override
    public String convert() {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String currentTime = startTime.format(timeFormatter);
        return "System started: [" + currentTime + "]";
    }
}
