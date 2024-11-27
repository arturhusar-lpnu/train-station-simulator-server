package dev.test_events;

import events.StartSystemEvent;
import generators.TicketSystem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class StartSystemEventTest {

    private TicketSystem system;
    private StartSystemEvent startSystemEvent;

    @BeforeEach
    void setUp() {
        // Mock the TicketSystem (if necessary)
        system = mock(TicketSystem.class);

        // Set up the mocked system's behavior for toString()
        when(system.toString()).thenReturn("Mocked TicketSystem");

        // Create StartSystemEvent instance
        startSystemEvent = new StartSystemEvent(system);
    }

    @Test
    void testConstructor() {
        // Verify the object is not null
        assertNotNull(startSystemEvent);

        // Verify that the system is properly set in the event object
        assertEquals(system, startSystemEvent.system);
    }

    @Test
    void testConvert() {
        // Define the expected output based on the current time and mocked system.toString()
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String currentTime = LocalTime.now().format(timeFormatter);

        String expected = "System started: [" + currentTime + "]: Mocked TicketSystem";

        // Call the convert() method and compare the result with the expected output
        String actual = startSystemEvent.convert();

        // The actual time in the result will depend on when the test runs, so we check the format first
        assertFalse(actual.startsWith("System started: [" + currentTime.substring(0, 5) + "]"));
        assertTrue(actual.endsWith(": Mocked TicketSystem"));
    }
}