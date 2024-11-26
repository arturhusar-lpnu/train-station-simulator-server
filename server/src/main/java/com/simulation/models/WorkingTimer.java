package com.simulation.models;

import com.simulation.services.SimulationService;
import com.simulation.events.EndSystemEvent;
import com.simulation.events.StartSystemEvent;
import com.simulation.generators.TicketSystem;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class WorkingTimer {
    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
    @Setter
    private SimulationService simulationService;
    private final TicketSystem ticketSystem;
    private final long durationMinutes;

    public WorkingTimer(long durationMinutes, TicketSystem ticketSystem) {
        this.durationMinutes = durationMinutes;
        this.ticketSystem = ticketSystem;
        simulationService = ticketSystem.getSimulationService();
    }

    public void startTimer() {
        ticketSystem.startSystem();
        scheduler.schedule(this::stopTimer, durationMinutes, TimeUnit.SECONDS);

        StartSystemEvent startSystemEvent = new StartSystemEvent(ticketSystem.getPayDeckSystem().getPayDecks(), LocalDateTime.now());
        simulationService.sendSimulationStartedEvent(startSystemEvent);
    }

    public void SetSimulationService(SimulationService simulationService) {
        this.simulationService = simulationService;
    }

    public void stopTimer() {
        ticketSystem.stopSystem();
        scheduler.shutdownNow();
        EndSystemEvent endSystemEvent = new EndSystemEvent(LocalDateTime.now());
        simulationService.sendSimulationStopEvent(endSystemEvent);
    }
}
