package event_listeners;

//================================================================================
// Events listeners to send data to front
//================================================================================

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;

@RequiredArgsConstructor
public class NetworkListener {
    private final SimpMessagingTemplate messagingTemplate;
}
