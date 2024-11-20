package events;

import generators.TicketSystem;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SimulationEvent extends Event {
    private final TicketSystem ticketSystem;
}
