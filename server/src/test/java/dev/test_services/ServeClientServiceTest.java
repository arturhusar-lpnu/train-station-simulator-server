package dev.test_services;

import com.simulation.services.ServeClientService;
import com.simulation.events.CrashPaydeckEvent;
import com.simulation.events.ModifiedQueueEvent;
import com.simulation.events.ServiceEvent;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@SpringBootTest(classes = ServeClientService.class)
public class ServeClientServiceTest {
    @Autowired
    private ServeClientService serveClientService;

    @MockBean
    private SimpMessagingTemplate messagingTemplate;

    @Test
    void testSendServiceEvent() {
        ServiceEvent serviceEvent = mock(ServiceEvent.class);   // Create your mock or real event
        serveClientService.sendServiceEvent(serviceEvent);

        // Verify the method was called on the messagingTemplate
        verify(messagingTemplate).convertAndSend("/topic/started-serving-client", serviceEvent);
    }

    @Test
    void testSendEndedServicingEvent() {
        ServiceEvent serviceEvent = mock(ServiceEvent.class);  // Create your mock or real event
        serveClientService.sendEndedServicingEvent(serviceEvent);

        // Verify the method was called on the messagingTemplate
        verify(messagingTemplate).convertAndSend("/topic/stoped-serving-client", serviceEvent);
    }

    @Test
    void testSendInterruptedServing() {
        CrashPaydeckEvent crashPaydeckEvent = mock(CrashPaydeckEvent.class);  // Create your mock or real event
        serveClientService.sendInterruptedServing(crashPaydeckEvent);

        // Verify the method was called on the messagingTemplate
        verify(messagingTemplate).convertAndSend("/topic/pay-deck-crush", crashPaydeckEvent);
    }

    @Test
    void testSendModifiedQueueEvent() {
        ModifiedQueueEvent modifiedQueueEvent = mock(ModifiedQueueEvent.class);  // Create your mock or real event
        serveClientService.sendModifiedQueueEvent(modifiedQueueEvent);

        // Verify the method was called on the messagingTemplate
        verify(messagingTemplate).convertAndSend("/topic/modified-queue", modifiedQueueEvent);
    }
}
