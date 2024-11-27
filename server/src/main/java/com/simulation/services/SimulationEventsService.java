package com.simulation.services;

import com.simulation.events.EndSystemEvent;
import com.simulation.events.StartSystemEvent;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class SimulationEventsService {
    private SimpMessagingTemplate messagingTemplate;

    public SimulationEventsService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }
    public void sendSimulationStartedEvent(StartSystemEvent startSystemEvent) {
        messagingTemplate.convertAndSend("/topic/simulation-started", startSystemEvent);
    }

    public void sendSimulationStopEvent(EndSystemEvent endSystemEvent) {
        messagingTemplate.convertAndSend("/topic/simulation-ended", endSystemEvent);
    }
}
