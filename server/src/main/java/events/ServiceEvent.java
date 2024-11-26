package events;

import lombok.Getter;

@Getter
public class ServiceEvent implements Event {
    private String payDeckId;
    private long servingTime;

    public ServiceEvent(String payDeckId, long servingTime) {
        this.payDeckId = payDeckId;
        this.servingTime = servingTime;
    }

    @Override
    public String convert() {
        return String.format("Service: PayDeckId=%d, ServingTime=%d",
                payDeckId, servingTime);
    }
}