package com.simulation.dtos;

import com.simulation.models.PayDeck;

import java.sql.Time;

public record PayDeckRecovered(PayDeck recoveredPayDeck, Time recoveredAt) {
}