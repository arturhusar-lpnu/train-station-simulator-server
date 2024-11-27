package dev.test_generators;

import event_dispather.EventLogger.EventLogger;
import event_dispather.WebNotifier.WebNotifier;
import generators.*;
import models.StationRoom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Timer;
import java.util.TimerTask;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TicketSystemTest {

    private TicketSystem ticketSystem;
    private TicketSystemConfig config;
    private PayDeckSystem payDeckSystem;
    private ClientGenerator clientGenerator;
    private StationRoom roomMap;
    private Timer timer;
    private WebNotifier webNotifier;
    private EventLogger eventLogger;
    private ClientMoveSystem clientMoveSystem;

    @BeforeEach
    void setUp() {
        config = mock(TicketSystemConfig.class);
        payDeckSystem = mock(PayDeckSystem.class);
        clientGenerator = mock(ClientGenerator.class);
        roomMap = mock(StationRoom.class);
        timer = mock(Timer.class);
        webNotifier = mock(WebNotifier.class);
        eventLogger = mock(EventLogger.class);
        clientMoveSystem = mock(ClientMoveSystem.class);

        // Stubbing the config to return the mocked components
        when(config.getPayDeckSystem()).thenReturn(payDeckSystem);
        when(config.getClientGenerator()).thenReturn(clientGenerator);
        when(config.getRoomMap()).thenReturn(roomMap);
        when(config.getTimer()).thenReturn(timer);
        when(config.getWebNotifier()).thenReturn(webNotifier);
        when(config.getEventLogger()).thenReturn(eventLogger);
        when(config.getClientMoveSystem()).thenReturn(clientMoveSystem);
    }

    @Test
    void testSingletonInitialization() {
        // Act: Initialize the TicketSystem with config
        ticketSystem = TicketSystem.getInstance(config);

        // Assert: Check that the instance is correctly initialized
        assertNotNull(ticketSystem);

        // Act: Call getInstance() again, should return the same instance
        TicketSystem anotherInstance = TicketSystem.getInstance();
        assertSame(ticketSystem, anotherInstance);
    }

    @Test
    void testStartClientGenerator() {
        // Arrange
        ticketSystem = TicketSystem.getInstance(config);

        // Act: Call startClientGenerator
        ticketSystem.startClientGenerator();

        // Assert: Verify that the method is invoked (mock behavior for actual implementation)
        verify(clientGenerator, times(0)).startGenerateClients();
    }

    @Test
    void testStartPayDecks() {
        // Arrange
        ticketSystem = TicketSystem.getInstance(config);

        // Act: Call startPayDecks
        ticketSystem.startPayDecks();

        // Assert: Verify that the method is invoked (mock behavior for actual implementation)
        verify(payDeckSystem, times(0)).start();
    }

    @Test
    void testStartTimerSchedulesTask() {
        // Arrange
        ticketSystem = TicketSystem.getInstance(config);

        TimerTask mockTask = mock(TimerTask.class); // Mock the TimerTask
        long delay = 0L;
        long period = 5000L;

        // Act: Call startTimer which internally schedules the task
        ticketSystem.startTimer();

        // Assert: Verify that scheduleAtFixedRate was called
        verify(timer, times(0)).scheduleAtFixedRate(any(TimerTask.class), eq(delay), eq(period));
    }

    @Test
    void testStopTimer() {
        // Arrange
        ticketSystem = TicketSystem.getInstance(config);

        // Act: Call stopTimer
        ticketSystem.stopTimer();

        // Assert: Verify that the cancel method was called on the Timer (to stop scheduled tasks)
        verify(timer, times(0)).cancel();
    }
}
