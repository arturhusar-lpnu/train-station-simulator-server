package events;

import models.Client;
import models.PayDeck;
import java.time.LocalDateTime;

class CreationEvent implements Event {
    private Client createdClient;
    private LocalDateTime time;
    private PayDeck choosedPaydeck;

    public CreationEvent(Client createdClient, LocalDateTime time, PayDeck choosedPaydeck) {
        this.createdClient = createdClient;
        this.time = time;
        this.choosedPaydeck = choosedPaydeck;
    }

    @Override
    public String convert() {
        return String.format("Creation: Client=%s, Time=%s, Paydeck=%s",
                createdClient, time, choosedPaydeck);
    }
}
