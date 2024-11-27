package com.simulation.strategy.time;

import com.simulation.exceptions.InvalidArgumentException;

import java.util.List;
import java.util.Random;

public class RandomTimeStrategy implements TimeGenerationStrategy{
    private final long minDelayTime;
    private final long maxDelayTime;

    public RandomTimeStrategy(List<Long> args) throws InvalidArgumentException {
        if (args == null || args.size() != 2) {
            throw new InvalidArgumentException("There must be exactly 2 arguments");
        }

        minDelayTime = args.stream().min(Long::compareTo).get();
        maxDelayTime = args.stream().max(Long::compareTo).get();
    }

    @Override
    public long getNextGenerationDelay() {
        Random rand = new Random();

        return rand.nextLong(maxDelayTime - minDelayTime + 1) + minDelayTime;
    }

}
