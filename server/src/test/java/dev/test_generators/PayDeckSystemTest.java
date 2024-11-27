package dev.test_generators;

import generators.PayDeckSystem;
import models.Client;
import models.PayDeck;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class PayDeckSystemTest {

    private PayDeckSystem payDeckSystem;
    private List<PayDeck> payDecks;
    private PayDeck payDeck1;
    private PayDeck payDeck2;
    private Client client;

    @BeforeEach
    void setUp() {
        payDecks = new ArrayList<>();
        payDeck1 = mock(PayDeck.class);
        payDeck2 = mock(PayDeck.class);
        payDecks.add(payDeck1);
        payDecks.add(payDeck2);

        client = mock(Client.class);

        // Initialize PayDeckSystem with two paydecks, no reserved paydeck, and thread pool size of 2
        payDeckSystem = new PayDeckSystem(payDecks, null, 2);
    }

    @Test
    void testAddClientToPayDeck() {
        // Arrange
        int payDeckId = 0;  // Assume we are adding client to payDeck1

        // Act
        payDeckSystem.addClient(payDeckId, client);

        // Assert: Verify that the client was added to payDeck1
        verify(payDeck1).addClient(client);
    }

    @Test
    void testAddClientToAnotherPayDeck() {
        // Arrange
        int payDeckId = 1;  // Assume we are adding client to payDeck2

        // Act
        payDeckSystem.addClient(payDeckId, client);

        // Assert: Verify that the client was added to payDeck2
        verify(payDeck2).addClient(client);
    }

    @Test
    void testConstructorInitialization() {
        // Act
        PayDeckSystem payDeckSystem = new PayDeckSystem(payDecks, payDeck1, 5);

        // Assert
        assertEquals(payDecks, payDeckSystem.getPayDecks());
        assertEquals(payDeck1, payDeckSystem.getReservedPayDeck());
        assertNotNull(payDeckSystem.getThreadPools());
        assertTrue(payDeckSystem.getThreadPools() instanceof ExecutorService);
    }

    @Test
    void testThreadPoolInitialization() {
        // Act
        ExecutorService threadPool = payDeckSystem.getThreadPools();

        // Assert: Ensure that the thread pool is initialized and it's an instance of ExecutorService
        assertNotNull(threadPool);
        assertTrue(threadPool instanceof ExecutorService);
    }

    @Test
    void testAddClientWithInvalidId() {
        // Arrange
        int invalidPayDeckId = 5;  // Invalid paydeck index

        // Act & Assert: Ensure that calling addClient with an invalid paydeck ID doesn't cause issues
        assertThrows(IndexOutOfBoundsException.class, () -> {
            payDeckSystem.addClient(invalidPayDeckId, client);
        });
    }
}