package com.simulation.dtos;

import java.time.LocalDateTime;

public record ServingClientEndedDto(LocalDateTime endTime, String payDeckId) {
}
