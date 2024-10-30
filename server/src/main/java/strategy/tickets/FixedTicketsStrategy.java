package strategy.tickets;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FixedTicketsStrategy implements TicketsGenerationStrategy {
    private final int ticketsCount;

    @Override
    public int getTickets() {
        return ticketsCount;
    }
}
