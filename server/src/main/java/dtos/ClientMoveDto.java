package dtos;

import models.*;

import java.util.List;

public record ClientMoveDto(Client client, PayDeck payDeck, List<Position> movements) {
}
