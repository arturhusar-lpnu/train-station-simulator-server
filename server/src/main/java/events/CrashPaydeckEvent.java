package events;

import lombok.Getter;
import models.Client;
import models.PayDeck;

import java.time.LocalDateTime;

@Getter
public class CrashPaydeckEvent implements Event {
    private PayDeck crashedPaydeck;
    private LocalDateTime startTime;
    private Client interruptedClient;

    public CrashPaydeckEvent(PayDeck crashedPaydeck, Client interruptedClient, LocalDateTime startTime) {
        this.crashedPaydeck = crashedPaydeck;
        this.interruptedClient = interruptedClient;
        this.startTime = startTime;
    }

    @Override
    public String convert() {
        return String.format("Crash: Paydeck=%s, StartTime=%s",
                crashedPaydeck, startTime);
    }
}