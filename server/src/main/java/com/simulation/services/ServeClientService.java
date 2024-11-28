package com.simulation.services;

import com.simulation.events.CrashPaydeckEvent;
import com.simulation.events.ModifiedQueueEvent;
import com.simulation.events.RecoveryPaydeckEvent;
import com.simulation.events.ServiceEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ServeClientService {
    private final SimpMessagingTemplate messagingTemplate;

    public void sendServiceEvent(ServiceEvent serviceEvent) { //SeviceStartedDto
        messagingTemplate.convertAndSend("/topic/started-serving-client", serviceEvent);
    }

    public void sendEndedServicingEvent(ServiceEvent serviceEvent) { //SeviceEndedDto
        messagingTemplate.convertAndSend("/topic/stoped-serving-client", serviceEvent);
    }

    public void sendCrashedDecks(CrashPaydeckEvent crushedEvent) {// crushed and reserved pay decks
        messagingTemplate.convertAndSend("/topic/pay-deck-crush", crushedEvent);
    }

    public void sendModifiedQueueEvent(ModifiedQueueEvent modifiedQueueEvent) {
        System.out.println("Sent modified queue " + modifiedQueueEvent);
        messagingTemplate.convertAndSend("/topic/modified-queue", modifiedQueueEvent);
    }
}
