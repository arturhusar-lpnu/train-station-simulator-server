package events;

import lombok.Getter;
import models.Client;
import models.PayDeck;

import java.sql.Time;

@Getter
public class ServiceEvent implements Event {
    private int payDeckId;
    private long servingTime;

    public ServiceEvent(int payDeckId, long servingTime) {
        this.payDeckId = payDeckId;
        this.servingTime = servingTime;
    }

    @Override
    public String convert() {
        return String.format("Service: PayDeckId=%d, ServingTime=%d",
                payDeckId, servingTime);
    }
}