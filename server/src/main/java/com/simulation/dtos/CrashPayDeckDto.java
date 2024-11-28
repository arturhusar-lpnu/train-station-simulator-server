package com.simulation.dtos;

import java.time.LocalDateTime;

public record CrashPayDeckDto(String crashedPayDeckId, String reservedPayDeckId, LocalDateTime createdAt) {
}
