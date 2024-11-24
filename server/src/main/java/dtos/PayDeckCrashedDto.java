package dtos;

import models.PayDeck;

import java.sql.Time;

public record PayDeckCrashedDto(PayDeck crushedPayDeck, Time crushedAt) {
}
