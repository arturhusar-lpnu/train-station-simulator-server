package com.simulation.dtos;

import com.simulation.models.Client;
import com.simulation.models.PayDeck;

public record ServiceDto(Client client, long servingTime, PayDeck servicePayDeck) {
}
