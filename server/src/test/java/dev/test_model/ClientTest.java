package dev.test_model;

import models.Client;
import models.Position;
import models.privileges.PrivilegeType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class ClientTest {

    private Client client;
    private Position position;

    @BeforeEach
    void setUp() {
        // Create a mock Position object
        position = new Position(1, 1);
        // Initialize the client object
        client = new Client(1, 5, position, PrivilegeType.Interrupted);
    }

    @Test
    void testInterruptChangesPrivilegeToInterrupted() {
        // Arrange: The client starts with 'Standard' privilege

        // Act: Call interrupt method
        client.interrupt();

        // Assert: Verify that privilege is set to 'Interrupted'
        assertEquals(PrivilegeType.Interrupted, client.getPrivilege());
    }

    @Test
    void testSetPositionChangesClientPosition() {
        // Arrange: A new position
        Position newPosition = new Position(2, 2);

        // Act: Set the new position
        client.setPosition(newPosition);

        // Assert: Verify that the client's position is updated
        assertEquals(newPosition, client.getCurrPosition());
    }

    @Test
    void testClientInitialization() {
        // Assert: Verify the client has correct initial values
        assertEquals(1, client.getId());
        assertEquals(5, client.getTicketsToBuy());
        assertEquals(position, client.getCurrPosition());
        assertEquals(PrivilegeType.Interrupted, client.getPrivilege());
    }

    @Test
    void testPrivilegeIsNotNullOnCreation() {
        // Assert: Ensure that privilege is set correctly at creation
        assertNotNull(client.getPrivilege());
    }
}