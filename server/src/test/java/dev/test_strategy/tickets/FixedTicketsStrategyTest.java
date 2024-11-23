package dev.test_strategy.tickets;

import exceptions.InvalidArgumentException;
import org.junit.jupiter.api.Test;
import strategy.tickets.FixedTicketsStrategy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FixedTicketsStrategyTest {

    @Test
    public void testConstructorWithInvalidArgs() {
        List<Integer> invalidArgs1 = Arrays.asList(5, 10);
        List<Integer> invalidArgs2 = null;
        List<Integer> invalidArgs3 = new ArrayList<>();
        assertThrows(InvalidArgumentException.class, () -> new FixedTicketsStrategy(invalidArgs1));
        assertThrows(InvalidArgumentException.class, () -> new FixedTicketsStrategy(invalidArgs2));
        assertThrows(InvalidArgumentException.class, () -> new FixedTicketsStrategy(invalidArgs3));
    }

    @Test
    public void testConstructorWithValidArgs() throws InvalidArgumentException {
        List<Integer> args = Arrays.asList(7); // Один аргумент
        FixedTicketsStrategy strategy = new FixedTicketsStrategy(args);
        assertNotNull(strategy);
    }

    @Test
    public void testGetTickets() throws InvalidArgumentException {
        List<Integer> args = Arrays.asList(5);
        FixedTicketsStrategy strategy = new FixedTicketsStrategy(args);

        int tickets = strategy.getTickets();
        assertEquals(5, tickets, "Tickets count should be 5");
    }
}
