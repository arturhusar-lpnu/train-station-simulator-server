package com.simulation.services;

import com.simulation.events.ClientChooseEvent;
import com.simulation.events.CreationEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ClientService {
    private final SimpMessagingTemplate messagingTemplate;

    public void sendClientCreatedEvent(CreationEvent creationEvent) {
        System.out.println("Created Client Message");
        messagingTemplate.convertAndSend("/topic/client-created", creationEvent);
    }
    public void sendClientChooseNewDeckEvent(ClientChooseEvent clientChooseEvent) {
        messagingTemplate.convertAndSend("/topic/THE-client-has-chosen", clientChooseEvent);
    }
}
