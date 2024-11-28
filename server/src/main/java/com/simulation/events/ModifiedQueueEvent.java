package com.simulation.events;

import com.simulation.models.PayDeck;
import lombok.Getter;

@Getter
public class ModifiedQueueEvent implements Event{ // Maybe throw list of clients
    private final PayDeck payDeck;

    public ModifiedQueueEvent(PayDeck payDeck) {
        this.payDeck = payDeck;
    }

    @Override
    public String convert() {
        return payDeck.toString();
    }
}
