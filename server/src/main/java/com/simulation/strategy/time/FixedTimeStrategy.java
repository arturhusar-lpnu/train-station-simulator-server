package com.simulation.strategy.time;

import com.simulation.exceptions.InvalidArgumentException;

import java.util.List;

public class FixedTimeStrategy implements TimeGenerationStrategy{
    private final long delayTime;

    public FixedTimeStrategy(List<Long> args) throws InvalidArgumentException {
        if (args == null || args.size() != 1) {
            throw new InvalidArgumentException("There must be exactly 1 arguments");
        }

        delayTime = args.get(0);
    }

    @Override
    public long getNextGenerationDelay() {
        return delayTime;
    }
}
