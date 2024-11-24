package dtos;

import models.*;

import java.sql.Time;

public record ServiceDto(Client client, Time startTime, Time endTime, PayDeck servicePayDeck, int ticketsCount) {
}
