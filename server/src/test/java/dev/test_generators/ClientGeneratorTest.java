package dev.test_generators;

import models.Client;
import generators.*;
import models.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import strategy.tickets.TicketsGenerationStrategy;
import strategy.time.TimeGenerationStrategy;

import java.util.List;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class ClientGeneratorTest {

    private ClientGenerator clientGenerator;
    private TimeGenerationStrategy timeGenerationStrategy;
    private PrivilegeGenerator privilegeGenerator;
    private TicketsGenerationStrategy ticketsGenerationStrategy;
    private ScheduledThreadPoolExecutor scheduler;

    @BeforeEach
    void setUp() {
        // Mocking the strategies
        timeGenerationStrategy = mock(TimeGenerationStrategy.class);
        privilegeGenerator = mock(PrivilegeGenerator.class);
        ticketsGenerationStrategy = mock(TicketsGenerationStrategy.class);

        // Initialize the ClientGenerator
        clientGenerator = new ClientGenerator(timeGenerationStrategy, privilegeGenerator, ticketsGenerationStrategy);
    }

    @Test
    void testConstructor() {
        // Ensure the clientGenerator is properly initialized
        assertNotNull(clientGenerator);
    }


    @Test
    void testStopGenerateClients() {
        // Mock the scheduler
        scheduler = mock(ScheduledThreadPoolExecutor.class);
        clientGenerator.setScheduler(scheduler); // Assuming you have a setter or directly access the scheduler

        // Call stopGenerateClients
        clientGenerator.stopGenerateClients();

        // Verify that shutdownNow() was called
        verify(scheduler, times(1)).shutdownNow();
    }

}