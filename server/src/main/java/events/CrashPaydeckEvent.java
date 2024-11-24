package events;

import lombok.Getter;
import models.PayDeck;

import java.time.LocalDateTime;

@Getter
public class CrashPaydeckEvent implements Event {
    private PayDeck crashedPaydeck;
    private LocalDateTime startTime;

    public CrashPaydeckEvent(PayDeck crashedPaydeck, LocalDateTime startTime) {
        this.crashedPaydeck = crashedPaydeck;
        this.startTime = startTime;
    }

    @Override
    public String convert() {
        return String.format("Crash: Paydeck=%s, StartTime=%s",
                crashedPaydeck, startTime);
    }
}