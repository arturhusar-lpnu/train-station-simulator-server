package dev.test_strategy.tickets;

import com.simulation.exceptions.InvalidArgumentException;
import org.junit.jupiter.api.Test;
import com.simulation.strategy.tickets.RandomTicketsStrategy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RandomTicketsStrategyTest {

    @Test
    public void testConstructorWithInvalidArgs() {
        List<Integer> invalidArgs1 = Arrays.asList(5);
        List<Integer> invalidArgs2 = null;
        List<Integer> invalidArgs3 = Arrays.asList(5, 6, 7, 8);
        List<Integer> invalidArgs4 = new ArrayList<>();
        assertThrows(InvalidArgumentException.class, () -> new RandomTicketsStrategy(invalidArgs1));
        assertThrows(InvalidArgumentException.class, () -> new RandomTicketsStrategy(invalidArgs2));
        assertThrows(InvalidArgumentException.class, () -> new RandomTicketsStrategy(invalidArgs3));
        assertThrows(InvalidArgumentException.class, () -> new RandomTicketsStrategy(invalidArgs4));
    }

    @Test
    public void testConstructorWithValidArgs() throws InvalidArgumentException {
        List<Integer> args = Arrays.asList(3, 8);
        RandomTicketsStrategy strategy = new RandomTicketsStrategy(args);
        assertNotNull(strategy);
    }

    @Test
    public void testGetTickets() throws InvalidArgumentException {
        List<Integer> args = Arrays.asList(5, 10);
        RandomTicketsStrategy strategy = new RandomTicketsStrategy(args);

        int testCount = 1000;
        for (int i = 0; i < testCount; i++) {
            int tickets = strategy.getTickets();
            assertTrue(tickets >= 5 && tickets <= 10, "Tickets count should be between 5 and 10");
        }
    }

    @Test
    public void testGetTicketsWithReversedArgs() throws InvalidArgumentException {
        List<Integer> args = Arrays.asList(10, 5);
        RandomTicketsStrategy strategy = new RandomTicketsStrategy(args);

        int testCount = 1000;
        for (int i = 0; i < testCount; i++) {
            int tickets = strategy.getTickets();
            assertTrue(tickets >= 5 && tickets <= 10, "Tickets count should be between 5 and 10");
        }
    }
}
