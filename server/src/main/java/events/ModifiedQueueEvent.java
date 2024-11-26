package events;

import models.PayDeck;

public class ModifiedQueueEvent implements Event{
    private PayDeck payDeck;

    public ModifiedQueueEvent(PayDeck payDeck) {
        this.payDeck = payDeck;
    }

    @Override
    public String convert() {
        return "";
    }
}
