package strategy.tickets;

import lombok.RequiredArgsConstructor;

import java.util.Random;

@RequiredArgsConstructor
public class RandomTicketsStrategy implements TicketsGenerationStrategy {
    private final int minTicketsCount;
    private final int maxTicketsCount;

    @Override
    public int getTickets() {
        Random rand = new Random();
        return rand.nextInt(maxTicketsCount - minTicketsCount + 1) + minTicketsCount;
    }
}
