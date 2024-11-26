package config;

import event_dispather.WebNotifier.WebNotifier;
import event_listeners.web.ServiceWebListener;
import events.ServiceEvent;
import org.springframework.messaging.simp.SimpMessagingTemplate;

public class WebNotifierConfig {
    private WebNotifier webNotifier;

    public WebNotifierConfig() {
        webNotifier = new WebNotifier();
    }

    public WebNotifier getWebNotifier() {
        webNotifier.subscribe(new ServiceEvent(0,0), new ServiceWebListener(new SimpMessagingTemplate(simpMessagingTemplate)));
    }
}
