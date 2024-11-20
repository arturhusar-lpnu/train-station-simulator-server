package events;

import events.Event;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import models.Client;
import models.PayDeck;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class PayDeskEvents {

    @Getter
    public static class PayDeskOpenedEvent extends PayDeskEvent {
        private final Thread workingThread;
        private final int payDeskId;

        public PayDeskOpenedEvent(PayDeck payDeck, Thread workingThread) {
            super(payDeck);
            this.workingThread = workingThread;
            this.payDeskId = payDeck.getId();
        }
    }

    @Getter
    public static class PayDeskBusyEvent extends PayDeskEvent {
        private final int remainingClientsCount;
        private final List<Client> remainingClients;

        public PayDeskBusyEvent(PayDeck payDeck, List<Client> remainingClients) {
            super(payDeck);
            this.remainingClients = new ArrayList<>(remainingClients);
            this.remainingClientsCount = remainingClients.size();
        }
    }

    @Getter
    public static class PayDeskFailureEvent extends PayDeskEvent {
        private final Client interruptedClient;
        private final PriorityQueue<Client> waitingClients;
        private final String failureReason;

        public PayDeskFailureEvent(PayDeck payDeck, Client interruptedClient,
                                   PriorityQueue<Client> waitingClients, String failureReason) {
            super(payDeck);
            this.interruptedClient = interruptedClient;
            this.waitingClients = new PriorityQueue<>(waitingClients);
            this.failureReason = failureReason;
        }
    }

    @Getter
    public static class PayDeskRepairedEvent extends PayDeskEvent {
        private final long repairTime;
        private final boolean isAvailableForService;

        public PayDeskRepairedEvent(PayDeck payDeck, long repairTime, boolean isAvailableForService) {
            super(payDeck);
            this.repairTime = repairTime;
            this.isAvailableForService = isAvailableForService;
        }
    }

    @Getter
    public static class PayDeskBecameBackupEvent extends PayDeskEvent {
        private final boolean wasWorking;
        private final int currentQueueSize;

        public PayDeskBecameBackupEvent(PayDeck payDeck, boolean wasWorking, int currentQueueSize) {
            super(payDeck);
            this.wasWorking = wasWorking;
            this.currentQueueSize = currentQueueSize;
        }
    }
}