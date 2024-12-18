package events;

import lombok.Getter;
import models.PayDeck;

import java.time.LocalDateTime;

@Getter
public class RecoveryPaydeckEvent implements Event {
    private PayDeck crashedPaydeck;
    private LocalDateTime endTime;

    public RecoveryPaydeckEvent(PayDeck crashedPaydeck, LocalDateTime endTime) {
        this.crashedPaydeck = crashedPaydeck;
        this.endTime = endTime;
    }

    @Override
    public String convert() {
        return String.format("Recovery: Paydeck=%s, EndTime=%s",
                crashedPaydeck, endTime);
    }
}