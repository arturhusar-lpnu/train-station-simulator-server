package strategy.time;

import lombok.RequiredArgsConstructor;

import java.util.Random;

@RequiredArgsConstructor
public class RandomTimeStrategy implements TimeGenerationStrategy{
    private final long minDelayTime;
    private final long maxDelayTime;

    @Override
    public long getNextGenerationDelay() {
        Random rand = new Random();

        return rand.nextLong(maxDelayTime - minDelayTime + 1) + minDelayTime;
    }

}
