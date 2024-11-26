package com.simulation.events;

import lombok.Getter;
import com.simulation.models.Client;
import com.simulation.models.Position;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class MoveEvent implements Event {
    private Client client;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private List<Position> route;

    public MoveEvent(Client client, LocalDateTime startTime, LocalDateTime endTime, List<Position> route) {
        this.client = client;
        this.startTime = startTime;
        this.endTime = endTime;
        this.route = route;
    }

    @Override
    public String convert() {
        return String.format("Move: Client=%s, Start=%s, End=%s, Route=%s",
                client, startTime, endTime, route);
    }
}