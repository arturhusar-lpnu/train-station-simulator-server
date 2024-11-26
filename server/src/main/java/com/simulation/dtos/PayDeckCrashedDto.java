package com.simulation.dtos;

import com.simulation.models.PayDeck;

import java.sql.Time;

public record PayDeckCrashedDto(PayDeck crushedPayDeck, Time crushedAt) {
}
