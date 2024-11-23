package dev.test_strategy.time;

import exceptions.InvalidArgumentException;
import org.junit.jupiter.api.Test;
import strategy.time.RandomTimeStrategy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RandomTimeStrategyTest {
    @Test
    public void testConstructorWithInvalidArgs() {
        List<Long> invalidArgs1 = Arrays.asList(5L);
        List<Long> invalidArgs2 = null;
        List<Long> invalidArgs3 = Arrays.asList(5L, 6L, 7L, 8L, 9L);
        List<Long> invalidArgs4 = new ArrayList<>();
        assertThrows(InvalidArgumentException.class, () -> new RandomTimeStrategy(invalidArgs1));
        assertThrows(InvalidArgumentException.class, () -> new RandomTimeStrategy(invalidArgs2));
        assertThrows(InvalidArgumentException.class, () -> new RandomTimeStrategy(invalidArgs3));
        assertThrows(InvalidArgumentException.class, () -> new RandomTimeStrategy(invalidArgs4));
    }

    @Test
    public void testConstructorWithValidArgs() throws InvalidArgumentException {
        List<Long> args = Arrays.asList(3L, 8L);
        RandomTimeStrategy strategy = new RandomTimeStrategy(args);
        assertNotNull(strategy);
    }

    @Test
    public void testGetNextGenerationDelay() throws InvalidArgumentException {
        List<Long> args = Arrays.asList(5L, 10L);
        RandomTimeStrategy strategy = new RandomTimeStrategy(args);

        int testCount = 1000;
        for (int i = 0; i < testCount; i++) {
            long delay = strategy.getNextGenerationDelay();
            assertTrue(delay >= 5L && delay <= 10L, "Delay should be between 5 and 10");
        }
    }

    @Test
    public void testGetNextGenerationDelayWithReversedArgs() throws InvalidArgumentException {
        List<Long> args = Arrays.asList(10L, 5L);
        RandomTimeStrategy strategy = new RandomTimeStrategy(args);

        int testCount = 1000;
        for (int i = 0; i < testCount; i++) {
            long delay = strategy.getNextGenerationDelay();
            assertTrue(delay >= 5L && delay <= 10L, "Delay should be between 5 and 10");
        }
    }
}
