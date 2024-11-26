package models;

import services.SimulationService;
import events.EndSystemEvent;
import events.StartSystemEvent;
import generators.TicketSystem;
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
    }

    public void startTimer() {
        ticketSystem.startSystem();
        scheduler.schedule(this::stopTimer, durationMinutes, TimeUnit.SECONDS);
        StartSystemEvent startSystemEvent = new StartSystemEvent(LocalDateTime.now());
        simulationService.sendSimulationStartedEvent(startSystemEvent);
    }

    public void stopTimer() {
        ticketSystem.stopSystem();
        scheduler.shutdownNow();
        EndSystemEvent endSystemEvent = new EndSystemEvent(LocalDateTime.now());
        simulationService.sendSimulationStopEvent(endSystemEvent);
    }
}
