package com.simulation.models;

import com.simulation.services.SimulationEventsService;
import com.simulation.events.EndSystemEvent;
import com.simulation.events.StartSystemEvent;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class WorkingTimer {
    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
    @Setter
    private SimulationEventsService simulationEventsService;
    private final TicketSystem ticketSystem;
    private final long durationMinutes;

    public WorkingTimer(long durationMinutes, TicketSystem ticketSystem) {
        this.durationMinutes = durationMinutes;
        this.ticketSystem = ticketSystem;
        simulationEventsService = ticketSystem.getSimulationEventsService();
    }

    public void startTimer() {
        ticketSystem.startSystem();
        scheduler.schedule(this::stopTimer, durationMinutes, TimeUnit.SECONDS);
        StartSystemEvent startSystemEvent = new StartSystemEvent(LocalDateTime.now(), ticketSystem.getPayDeckSystem().getPayDecks());
        simulationEventsService.sendSimulationStartedEvent(startSystemEvent);
    }

    public void stopTimer() {
        System.out.println("Timer stopped");
        ticketSystem.stopSystem();
        scheduler.shutdownNow();
        EndSystemEvent endSystemEvent = new EndSystemEvent(LocalDateTime.now());
        simulationEventsService.sendSimulationStopEvent(endSystemEvent);
    }
}
