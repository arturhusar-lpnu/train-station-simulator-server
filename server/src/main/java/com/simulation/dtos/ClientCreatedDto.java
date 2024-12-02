package com.simulation.dtos;

//================================================================================
// Client Objects sent to front end
//================================================================================

import com.simulation.models.Client;

public record ClientCreatedDto(Client createdClient, String chosenPayDeckId) {
}
