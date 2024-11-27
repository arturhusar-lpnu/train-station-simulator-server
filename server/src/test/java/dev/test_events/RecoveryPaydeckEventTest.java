package dev.test_events;

import events.RecoveryPaydeckEvent;
import models.PayDeck;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class RecoveryPaydeckEventTest {

    private PayDeck payDeck;
    private LocalDateTime endTime;
    private RecoveryPaydeckEvent recoveryPaydeckEvent;

    @BeforeEach
    void setUp() {
        // Create a PayDeck instance (this can be a mock or a real instance)
        payDeck = new PayDeck(1); // Use an actual PayDeck object if it's a simple class, or mock it if needed.

        // Set a specific end time
        endTime = LocalDateTime.of(2024, 11, 27, 15, 0);

        // Create RecoveryPaydeckEvent with the PayDeck and endTime
        recoveryPaydeckEvent = new RecoveryPaydeckEvent(payDeck, endTime);
    }

    @Test
    void testConstructor() {
        // Verify the object was created successfully
        assertNotNull(recoveryPaydeckEvent);

        // Verify the internal state matches the expected values
        assertEquals(payDeck, recoveryPaydeckEvent.getCrashedPaydeck());
        assertEquals(endTime, recoveryPaydeckEvent.getEndTime());
    }

    @Test
    void testConvert() {
        // Define the expected string based on the data passed to the constructor
        String expected = String.format("Recovery: Paydeck=%s, EndTime=%s", payDeck, endTime);

        // Call the convert() method on the RecoveryPaydeckEvent instance
        String actual = recoveryPaydeckEvent.convert();

        // Compare expected and actual values
        assertEquals(expected, actual);
    }
}