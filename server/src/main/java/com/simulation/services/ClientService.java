package com.simulation.services;

import com.simulation.dtos.ClientCreatedDto;
import com.simulation.events.CreationEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ClientService {
    private final SimpMessagingTemplate messagingTemplate;

    public void sendClientCreatedEvent(CreationEvent creationEvent) {
        String clientId = creationEvent.getCreatedClient().getId();
        String chosenPayDeckId = creationEvent.getChoosedPaydeck().getId();
        ClientCreatedDto clientCreatedDto = new ClientCreatedDto(clientId, chosenPayDeckId);
        System.out.println("Created Client Message");
        messagingTemplate.convertAndSend("/topic/client-created", clientCreatedDto);
    }
}
