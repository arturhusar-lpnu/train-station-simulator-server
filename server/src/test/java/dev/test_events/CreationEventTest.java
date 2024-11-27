package dev.test_events;

import events.CreationEvent;
import models.Client;
import models.PayDeck;
import models.Position;
import models.privileges.PrivilegeType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Fail.fail;

public class CreationEventTest {

    private Client client;
    private PayDeck payDeck;
    private LocalDateTime time;
    private CreationEvent creationEvent;

    @BeforeEach
    void setUp() {
        // Initialize Position and PrivilegeType (assuming these are properly defined elsewhere)
        Position position = new Position(4, 4); // Example Position (adjust as necessary)
        PrivilegeType privilege = PrivilegeType.Military; // Example privilege (adjust as necessary)

        // Create a Client with specific values
        client = new Client(1, 3, position, privilege); // Example values for id, ticketsToBuy, position, and privilege

        // Initialize PayDeck (assuming PayDeck has a no-argument constructor or setter)
        payDeck = new PayDeck(1); // Adjust according to the actual PayDeck setup

        // Set a specific time for the event
        time = LocalDateTime.of(2024, 11, 27, 14, 30);

        // Create the CreationEvent instance
        creationEvent = new CreationEvent(client, time, payDeck);
    }

    @Test
    void testConstructor() {
        // Custom verification: Check the created object's internal properties
        if (creationEvent.getCreatedClient() == null) {
            fail("Created Client should not be null.");
        }
        if (creationEvent.getTime() == null) {
            fail("Time should not be null.");
        }
        if (creationEvent.getChoosedPaydeck() == null) {
            fail("Choosed Paydeck should not be null.");
        }

        // Check if properties match expected values manually
        if (!creationEvent.getCreatedClient().equals(client)) {
            fail("Created client does not match the expected client.");
        }
        if (!creationEvent.getTime().equals(time)) {
            fail("Time does not match the expected time.");
        }
        if (!creationEvent.getChoosedPaydeck().equals(payDeck)) {
            fail("Choosed Paydeck does not match the expected PayDeck.");
        }
    }

    @Test
    void testConvert() {
        // Manually verify the output of the convert() method
        String expected = String.format("Creation: Client=%s, Time=%s, Paydeck=%s", client, time, payDeck);
        String actual = creationEvent.convert();

        if (!expected.equals(actual)) {
            fail("Convert method did not return the expected string. Expected: " + expected + ", but got: " + actual);
        }
    }
}
