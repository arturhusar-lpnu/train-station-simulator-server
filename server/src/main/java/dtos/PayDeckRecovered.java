package dtos;

import models.PayDeck;

import java.sql.Time;

public record PayDeckRecovered(PayDeck recoveredPayDeck, Time recoveredAt) {
}