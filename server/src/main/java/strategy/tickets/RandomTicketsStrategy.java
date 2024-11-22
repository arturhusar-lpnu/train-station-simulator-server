package strategy.tickets;

import exceptions.InvalidArgumentException;

import java.util.List;
import java.util.Random;

public class RandomTicketsStrategy implements TicketsGenerationStrategy {
    private final int minTicketsCount;
    private final int maxTicketsCount;

    public RandomTicketsStrategy(List<Integer> args) throws InvalidArgumentException {
        if (args == null || args.size() != 2) {
            throw new InvalidArgumentException("There must be exactly 2 arguments");
        }

        minTicketsCount = args.stream().min(Integer::compareTo).get();
        maxTicketsCount = args.stream().max(Integer::compareTo).get();
    }

    @Override
    public int getTickets() {
        Random rand = new Random();
        return rand.nextInt(maxTicketsCount - minTicketsCount + 1) + minTicketsCount;
    }
}
