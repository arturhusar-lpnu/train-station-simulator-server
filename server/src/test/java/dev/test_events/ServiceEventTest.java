package dev.test_events;

import models.Client;
import events.ServiceEvent;
import models.PayDeck;
import models.Position;
import models.privileges.PrivilegeType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Time;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ServiceEventTest {

    private Client client;
    private Time startTime;
    private Time endTime;
    private PayDeck choosedPaydeck;
    private int ticketsCount;
    private ServiceEvent serviceEvent;

    @BeforeEach
    void setUp() {
        // Create a PayDeck instance (mock or real depending on implementation)
        choosedPaydeck = new PayDeck(1); // Replace with actual or mock PayDeck object

        // Create a Client instance (mock or real depending on implementation)
        client = new Client(1, 3, new Position(4, 4), PrivilegeType.Interrupted); // Example values

        // Set the start and end times using the Time class
        startTime = Time.valueOf("14:30:00");
        endTime = Time.valueOf("15:00:00");

        // Set the ticket count
        ticketsCount = 5;

        // Create ServiceEvent with all the necessary values
        serviceEvent = new ServiceEvent(client, startTime, endTime, choosedPaydeck, ticketsCount);
    }

    @Test
    void testConstructor() {
        // Verify the object was created successfully
        assertNotNull(serviceEvent);

        // Verify the internal state matches the expected values
        assertEquals(client, serviceEvent.getClient());
        assertEquals(startTime, serviceEvent.getStartTime());
        assertEquals(endTime, serviceEvent.getEndTime());
        assertEquals(choosedPaydeck, serviceEvent.getChoosedPaydeck());
        assertEquals(ticketsCount, serviceEvent.getTicketsCount());
    }

    @Test
    void testConvert() {
        // Define the expected string based on the data passed to the constructor
        String expected = String.format("Service: Client=%s, Start=%s, End=%s, Paydeck=%s, Tickets=%d",
                client, startTime, endTime, choosedPaydeck, ticketsCount);

        // Call the convert() method on the ServiceEvent instance
        String actual = serviceEvent.convert();

        // Compare expected and actual values
        assertEquals(expected, actual);
    }
}