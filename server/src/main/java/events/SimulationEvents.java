package events;

import generators.TicketSystem;

public class SimulationEvents {
    public static class SimulationStartedEvent extends SimulationEvent {
        public SimulationStartedEvent(TicketSystem ticketSystem) {
            super(ticketSystem);
        }
    }

    public static class SimulationBreak
}
