package com.simulation.events;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ServiceEvent implements Event {
    private final String payDeckId;
    private final long servingTime;
    private final LocalDateTime createdAt;

    public ServiceEvent(String payDeckId, long servingTime, LocalDateTime createdAt) {
        this.payDeckId = payDeckId;
        this.servingTime = servingTime;
        this.createdAt = createdAt;
    }

    @Override
    public String convert() {
        return String.format("Service: PayDeckId=%d, ServingTime=%d",
                payDeckId, servingTime);
    }
}