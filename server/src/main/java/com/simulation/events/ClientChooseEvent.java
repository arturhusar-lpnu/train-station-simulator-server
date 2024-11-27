package com.simulation.events;

import com.simulation.models.Client;
import com.simulation.models.PayDeck;
import lombok.Getter;

@Getter
public class ClientChooseEvent implements Event {
    private final Client createdClient;
    private final PayDeck choosedPaydeck;

    public ClientChooseEvent(Client createdClient, PayDeck choosedPaydeck) {
        this.createdClient = createdClient;
        this.choosedPaydeck = choosedPaydeck;
    }

    @Override
    public String convert() {
        return String.format("Creation: Client=%s, Paydeck=%s",
                createdClient, choosedPaydeck);
    }
}
