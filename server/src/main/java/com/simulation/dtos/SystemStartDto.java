package com.simulation.dtos;

import java.time.LocalDateTime;
import java.util.List;

public record SystemStartDto(LocalDateTime startTime, List<String> payDeckIds) {
}
