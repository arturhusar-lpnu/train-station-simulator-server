package com.simulation.services;

import com.simulation.dtos.ClientCreatedDto;
import com.simulation.events.CreationEvent;
import com.simulation.models.Client;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ClientService {
    private final SimpMessagingTemplate messagingTemplate;

    public void sendClientCreatedEvent(CreationEvent creationEvent) {
        Client createdClient = creationEvent.getCreatedClient();
        String chosenPayDeckId = creationEvent.getChoosedPaydeck().getId();
        ClientCreatedDto clientCreatedDto = new ClientCreatedDto(createdClient, chosenPayDeckId);
        System.out.println("Created Client Message " + clientCreatedDto);
        messagingTemplate.convertAndSend("/topic/client-created", clientCreatedDto);
    }
}
