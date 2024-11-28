package com.simulation.dtos;

import com.simulation.models.Client;

//================================================================================
// Client Objects sent to front end
//================================================================================

public record ClientCreatedDto(String clientId, String chosenPayDeckId) {
}
