package events;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import models.PayDeck;

@RequiredArgsConstructor
@Getter
public abstract class PayDeskEvent extends Event {
    private final PayDeck payDeck;
}
