package com.simulation.events;

import com.simulation.models.PayDeck;

public class ModifiedQueueEvent implements Event{ // Maybe throw list of clients
    private PayDeck payDeck;

    public ModifiedQueueEvent(PayDeck payDeck) {
        this.payDeck = payDeck;
    }

    @Override
    public String convert() {
        return "";
    }
}
