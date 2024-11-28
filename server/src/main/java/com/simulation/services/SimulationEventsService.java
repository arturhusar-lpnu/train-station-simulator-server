package com.simulation.services;

import com.simulation.dtos.SystemEndDto;
import com.simulation.dtos.SystemStartDto;
import com.simulation.events.EndSystemEvent;
import com.simulation.events.StartSystemEvent;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class SimulationEventsService {
    private final SimpMessagingTemplate messagingTemplate;

    public SimulationEventsService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void sendSimulationStartedEvent(StartSystemEvent startSystemEvent) {
        SystemStartDto systemStartDto = new SystemStartDto(startSystemEvent.getStartTime(), startSystemEvent.getPayDeckIds());
        messagingTemplate.convertAndSend("/topic/simulation-started", systemStartDto);
    }

    public void sendSimulationStopEvent(EndSystemEvent endSystemEvent) {
        SystemEndDto systemEndDto = new SystemEndDto(endSystemEvent.getEndTime());
        messagingTemplate.convertAndSend("/topic/simulation-ended", systemEndDto);
    }
}
