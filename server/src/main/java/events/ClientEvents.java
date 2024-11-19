package events;

import lombok.RequiredArgsConstructor;
import models.Client;
import models.PayDeck;
import models.Position;

public class ClientEvents {
    @RequiredArgsConstructor
    public static class ClientCreatedEvent extends Event {
        private final Client client;
        public Client getClient() {
            return client;
        }
    }
    @RequiredArgsConstructor
    public static class ClientMovedEvent extends Event {
        private final Client client;
        private final Position oldPosition;
        private final Position newPosition;
    }

    @RequiredArgsConstructor
    public static class ClientQueuedEvent extends Event {
        private final Client client;
        private final PayDeck payDesk;
    }

    @RequiredArgsConstructor
    public static class ClientServiceStartedEvent extends Event {
        private final Client client;
        private final PayDeck payDesk;
    }

    @RequiredArgsConstructor
    public static class ClientServiceFinishedEvent extends Event {
        private final Client client;
        private final PayDeck payDeck;
        private final boolean successful;
    }

    @RequiredArgsConstructor
    public static class ClientExitedEvent extends Event {
        private final Client client;
    }
}
