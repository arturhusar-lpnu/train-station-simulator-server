package com.simulation.services;

import com.simulation.dtos.CrashPayDeckDto;
import com.simulation.dtos.ModifiedQueueDto;
import com.simulation.dtos.ServingClientEndedDto;
import com.simulation.dtos.ServingClientStartedDto;
import com.simulation.events.CrashPaydeckEvent;
import com.simulation.events.ModifiedQueueEvent;
import com.simulation.events.ServiceEvent;
import com.simulation.models.Client;
import com.simulation.models.PayDeck;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ServeClientService {
    private final SimpMessagingTemplate messagingTemplate;

    public void sendServiceEvent(ServiceEvent serviceEvent) {
        ServingClientStartedDto servingStartedDto = new ServingClientStartedDto(serviceEvent.getPayDeckId(), serviceEvent.getServingTime());
        messagingTemplate.convertAndSend("/topic/started-serving-client", servingStartedDto);
    }

    public void sendEndedServicingEvent(ServiceEvent serviceEvent) {
        ServingClientEndedDto servingEndedDto = new ServingClientEndedDto(serviceEvent.getCreatedAt(), serviceEvent.getPayDeckId());
        messagingTemplate.convertAndSend("/topic/stopped-serving-client", servingEndedDto);
    }

    public void sendCrashedDecks(CrashPaydeckEvent crushedEvent) {// crushed and reserved pay decks
        CrashPayDeckDto crashPayDeckDto = new CrashPayDeckDto(crushedEvent.getCrashedPaydeck().getId(),
                                                                crushedEvent.getReservedPaydeck().getId(),
                                                                crushedEvent.getCreatedAt());

        messagingTemplate.convertAndSend("/topic/pay-deck-crush", crashPayDeckDto);
    }

    public void sendModifiedQueueEvent(ModifiedQueueEvent modifiedQueueEvent) {
        System.out.println("Sent modified queue " + modifiedQueueEvent);
        PayDeck payDeck = modifiedQueueEvent.getPayDeck();
        String payDeckId = payDeck.getId();
        List<String> clientIds = payDeck.getClientsQueue().stream().map(Client::getId).toList();
        ModifiedQueueDto modifiedQueueDto = new ModifiedQueueDto(payDeckId, clientIds);

        messagingTemplate.convertAndSend("/topic/modified-queue", modifiedQueueDto);
    }
}
