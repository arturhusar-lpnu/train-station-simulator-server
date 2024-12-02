package com.simulation.services;

import com.simulation.generators.ClientGenerator;
import com.simulation.generators.PayDeckSystem;
import com.simulation.models.Client;

public class ClientRemovalService {
    private PayDeckSystem payDeckSystem;
    private ClientGenerator clientGenerator;

    public ClientRemovalService(PayDeckSystem payDeckSystem, ClientGenerator clientGenerator) {
          this.payDeckSystem = payDeckSystem;
          this.clientGenerator = clientGenerator;
    }

    public void removeClient(Client client) {
        var payDeck = payDeckSystem.getPayDecks().stream().filter(p -> p.getClientsQueue().contains(client)).findFirst().get();
        clientGenerator.getClients().remove(client);
        payDeck.getClientsQueue().remove(client);
    }
}
