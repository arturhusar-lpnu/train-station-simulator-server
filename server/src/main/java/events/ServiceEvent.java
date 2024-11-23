package events;

import models.Client;
import models.PayDeck;

import java.sql.Time;

class ServiceEvent implements Event {
    private Client client;
    private Time startTime;
    private Time endTime;
    private PayDeck choosedPaydeck;
    private int ticketsCount;

    public ServiceEvent(Client client, Time startTime, Time endTime,
                        PayDeck choosedPaydeck, int ticketsCount) {
        this.client = client;
        this.startTime = startTime;
        this.endTime = endTime;
        this.choosedPaydeck = choosedPaydeck;
        this.ticketsCount = ticketsCount;
    }

    @Override
    public String convert() {
        return String.format("Service: Client=%s, Start=%s, End=%s, Paydeck=%s, Tickets=%d",
                client, startTime, endTime, choosedPaydeck, ticketsCount);
    }
}