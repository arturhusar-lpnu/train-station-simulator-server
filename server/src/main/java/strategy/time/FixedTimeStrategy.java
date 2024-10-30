package strategy.time;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FixedTimeStrategy implements TimeGenerationStrategy{
    private final long delayTime;

    @Override
    public long getNextGenerationDelay() {
        return delayTime;
    }
}
