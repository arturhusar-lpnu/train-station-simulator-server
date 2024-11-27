package com.simulation.dtos;

import java.util.List;

public record ConfigDto(String startOfWorkingDay, String endOfWorkingDay, int payDecksCount, List<Long> timeStrategyArguments, List<Integer> ticketsStrategyArguments) {



}
