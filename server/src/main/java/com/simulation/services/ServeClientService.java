package com.simulation.services;

import com.simulation.events.CrashPaydeckEvent;
import com.simulation.events.ModifiedQueueEvent;
import com.simulation.events.RecoveryPaydeckEvent;
import com.simulation.events.ServiceEvent;
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

    public void sendInterruptedServing(CrashPaydeckEvent interuptedEvent) {
        messagingTemplate.convertAndSend("/topic/pay-deck-crush", interuptedEvent);

    }

    public void sendModifiedQueueEvent(ModifiedQueueEvent modifiedQueueEvent) {
        messagingTemplate.convertAndSend("/topic/modified-queue", modifiedQueueEvent);
    }

    public void sendRecoveredDeckEvent(RecoveryPaydeckEvent recoveredDeckEvent) {
        messagingTemplate.convertAndSend("/topic/recovered-deck", recoveredDeckEvent);
    }
}
