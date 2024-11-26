package dtos;

import models.*;

public record ServiceDto(Client client, long servingTime, PayDeck servicePayDeck) {
}
