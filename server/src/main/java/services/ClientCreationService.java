package services;

import events.CreationEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ClientCreationService {
    private final SimpMessagingTemplate messagingTemplate;

    public void sendClientCreatedEvent(CreationEvent creationEvent) {
        messagingTemplate.convertAndSend("/topic/client-created", creationEvent);
    }
}
