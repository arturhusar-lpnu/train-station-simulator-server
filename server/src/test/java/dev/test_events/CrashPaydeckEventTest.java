package dev.test_events;

import events.CrashPaydeckEvent;
import models.PayDeck;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

public class CrashPaydeckEventTest {

    private PayDeck payDeck;
    private LocalDateTime startTime;
    private CrashPaydeckEvent crashPaydeckEvent;

    @BeforeEach
    void setUp() {
        // Initialize objects before each test
        payDeck = new PayDeck(1); // assuming there's a constructor or setter in PayDeck
        startTime = LocalDateTime.of(2024, 11, 27, 14, 30);
        crashPaydeckEvent = new CrashPaydeckEvent(payDeck, startTime);
    }

    @Test
    void testConstructor() {
        assertNotNull(crashPaydeckEvent);
        assertEquals(payDeck, crashPaydeckEvent.getCrashedPaydeck());
        assertEquals(startTime, crashPaydeckEvent.getStartTime());
    }

    @Test
    void testConvert() {
        String expected = String.format("Crash: Paydeck=%s, StartTime=%s", payDeck, startTime);
        assertEquals(expected, crashPaydeckEvent.convert());
    }
}