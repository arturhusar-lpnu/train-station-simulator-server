package com.simulation.strategy.tickets;

import com.simulation.exceptions.InvalidArgumentException;

import java.util.List;

public class FixedTicketsStrategy implements TicketsGenerationStrategy {
    private final int ticketsCount;

    public FixedTicketsStrategy(List<Integer> args) throws InvalidArgumentException {
        if (args == null || args.size() != 1) {
            throw new InvalidArgumentException("There must be exactly 1 arguments");
        }

        this.ticketsCount = args.get(0);
    }

    @Override
    public int getTickets() {
        return ticketsCount;
    }
}
