package dtos;

import models.Client;
import models.PayDeck;
import models.Position;

import java.util.List;

//================================================================================
// Client Objects sent to front end with movement
//================================================================================
public record ClientDTO(Client client, PayDeck payDeck, List<Position> movements, double time) {
}
