package events;

import lombok.Getter;
import models.Client;
import models.PayDeck;


public class ClientEvents {

    @Getter
    public static class ClientCreatedEvent extends ClientEvent {
        public ClientCreatedEvent(Client client) {
            super(client);
        }
    }
//    @Getter
//    public static class ClientEnteredQueueEvent extends ClientEvent {
//        public final PayDeck servingDeck;
//        public ClientEnteredQueueEvent(Client client, PayDeck servingDeck) {
//            super(client);
//            this.servingDeck = servingDeck;
//        }
//    }

    @Getter
    public static class ClientServedEvent extends ClientEvent {
        private final PayDeck payDeck;
        private final boolean successful;

        public ClientServedEvent(Client client, final PayDeck payDeck, final boolean successful) {
            super(client);
            this.payDeck = payDeck;
            this.successful = successful;
        }
    }
}
