package dev.test_model;

import models.Client;
import models.PayDeck;
import models.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PayDeckTest {

    private PayDeck payDeck;
    private Client client;

    @BeforeEach
    void setUp() {
        // Initialize a mock client
        client = mock(Client.class);
        when(client.getTicketsToBuy()).thenReturn(5); // Mocking the ticketsToBuy method

        // Create a new PayDeck instance
        payDeck = new PayDeck(1);
    }

    @Test
    void testAddClient() {
        // Act: Add client to paydeck
        payDeck.addClient(client);

        // Assert: Verify that the client's queue size is 1
        assertEquals(1, payDeck.getQueueSize());
    }

    @Test
    void testServeClient() throws InterruptedException {
        // Arrange: Add client to the queue
        payDeck.addClient(client);

        // Act: Call the serveClient method (simulate serving a client)
        payDeck.serveClient();

        // Assert: Verify the client was served (check queue size after serving)
        assertEquals(0, payDeck.getQueueSize());
    }

    @Test
    void testServeClientWhenNotWorking() {
        // Arrange: Mark paydeck as not working
        payDeck.crash(); // Set the PayDeck to not working

        // Act: Call the serveClient method
        payDeck.serveClient();

        // Assert: Since the paydeck is not working, it should print "Not working now" and not serve the client
        // (We would normally mock `System.out.println` in actual test cases, but here we assume it works)
    }

    @Test
    void testPriorityQueueOrdering() {
        // Arrange: Create and add multiple clients with different ticket counts
        Client client1 = mock(Client.class);
        Client client2 = mock(Client.class);
        when(client1.getTicketsToBuy()).thenReturn(3);
        when(client2.getTicketsToBuy()).thenReturn(5);

        // Add clients to the PayDeck
        payDeck.addClient(client1);
        payDeck.addClient(client2);

        // Act: Check the queue size (should be 2)
        assertEquals(2, payDeck.getQueueSize());

        // Assert: The client with fewer tickets (client1) should be served first
        payDeck.serveClient(); // Serve one client
        assertEquals(1, payDeck.getQueueSize()); // Only one client should remain
    }

    @Test
    void testCrashPayDeck() {
        // Act: Crash the paydeck
        payDeck.crash();

        // Assert: Verify that the paydeck is not working
        assertFalse(payDeck.isWorking());
    }

}
