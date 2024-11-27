package dev.test_strategy.time;

import com.simulation.exceptions.InvalidArgumentException;
import org.junit.jupiter.api.Test;
import com.simulation.strategy.time.FixedTimeStrategy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FixedTimeStrategyTest {

    @Test
    public void testConstructorWithInvalidArgs() {
        List<Long> invalidArgs1 = Arrays.asList(5L, 10L);
        List<Long> invalidArgs2 = null;
        List<Long> invalidArgs3 = new ArrayList<>();
        assertThrows(InvalidArgumentException.class, () -> new FixedTimeStrategy(invalidArgs1));
        assertThrows(InvalidArgumentException.class, () -> new FixedTimeStrategy(invalidArgs2));
        assertThrows(InvalidArgumentException.class, () -> new FixedTimeStrategy(invalidArgs3));
    }

    @Test
    public void testConstructorWithValidArgs() throws InvalidArgumentException {
        List<Long> args = Arrays.asList(7L); // Один аргумент
        FixedTimeStrategy strategy = new FixedTimeStrategy(args);
        assertNotNull(strategy);
    }

    @Test
    public void testGetNextGenerationDelay() throws InvalidArgumentException {
        List<Long> args = Arrays.asList(5L);
        FixedTimeStrategy strategy = new FixedTimeStrategy(args);

        long tickets = strategy.getNextGenerationDelay();
        assertEquals(5, tickets, "Tickets count should be 5");
    }
}
