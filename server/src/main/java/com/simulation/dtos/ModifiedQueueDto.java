package com.simulation.dtos;

import java.util.List;

public record ModifiedQueueDto(String payDeckID, List<String> deckClientIds) {
}
