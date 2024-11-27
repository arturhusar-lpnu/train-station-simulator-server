package dev.test_events;
import events.MoveEvent;
import models.Position;
import models.Client;

import models.privileges.PrivilegeType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class MoveEventTest {

    private Client client;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private List<Position> route;
    private MoveEvent moveEvent;

    @BeforeEach
    void setUp() {
        // Initialize Position and PrivilegeType (assuming these are properly defined elsewhere)
        Position position1 = new Position(1, 1); // Example Position (adjust as necessary)
        Position position2 = new Position(2, 2); // Example Position (adjust as necessary)

        // Create a Client with specific values
        client = new Client(1, 3, new Position(4, 4), PrivilegeType.Interrupted); // Example values

        // Set start and end times for the event
        startTime = LocalDateTime.of(2024, 11, 27, 14, 30);
        endTime = LocalDateTime.of(2024, 11, 27, 15, 30);

        // Create a route with two positions
        route = Arrays.asList(position1, position2);

        // Create MoveEvent with the provided values
        moveEvent = new MoveEvent(client, startTime, endTime, route);
    }

    @Test
    void testConstructor() {
        // Verify the object was created successfully
        assertNotNull(moveEvent);

        // Verify the internal state matches the expected values
        assertEquals(client, moveEvent.getClient());
        assertEquals(startTime, moveEvent.getStartTime());
        assertEquals(endTime, moveEvent.getEndTime());
        assertEquals(route, moveEvent.getRoute());
    }

    @Test
    void testConvert() {
        // Define the expected string based on the data passed to the constructor
        String expected = String.format("Move: Client=%s, Start=%s, End=%s, Route=%s", client, startTime, endTime, route);

        // Call the convert() method on the MoveEvent instance
        String actual = moveEvent.convert();

        // Compare expected and actual values
        assertEquals(expected, actual);
    }
}