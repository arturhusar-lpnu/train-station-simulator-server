package event_listeners.web;

import events.ModifiedQueueEvent;
import events.ServiceEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

//@Component
//public class ServiceWebListener extends WebListener{
//    public ServiceWebListener(SimpMessagingTemplate messagingTemplate) {
//        super(messagingTemplate);
//    }
//    @Override
//    public void update(Event event) {
//        if(event instanceof ServiceEvent) {
//            messagingTemplate.convertAndSend("/topic/pay-decks/service", event);
//        }
//    }
//}
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

    public void sendModifiedQueueEvent(ModifiedQueueEvent modifiedQueueEvent) {
        messagingTemplate.convertAndSend("/topic/modified-queue", modifiedQueueEvent);
    }
}
