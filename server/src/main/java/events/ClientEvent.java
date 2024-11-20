package events;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import models.Client;

@Getter
public class ClientEvent extends Event {
    private Client client;
    public ClientEvent(Client client) {
        this.client = client;
    }
}
