package com.simulation.events;

import lombok.Getter;
import com.simulation.models.Client;
import com.simulation.models.PayDeck;

import java.time.LocalDateTime;

@Getter
public class CrashPaydeckEvent implements Event {
    private final PayDeck crashedPaydeck;
    private final LocalDateTime startTime;
    private final PayDeck reservedPaydeck;

    public CrashPaydeckEvent(PayDeck crashedPaydeck, PayDeck reservedPaydeck, LocalDateTime startTime) {
        this.crashedPaydeck = crashedPaydeck;
        this.reservedPaydeck = reservedPaydeck;
        this.startTime = startTime;
    }

    @Override
    public String convert() {
        return String.format("Crash: Paydeck=%s, StartTime=%s",
                crashedPaydeck, startTime);
    }
}