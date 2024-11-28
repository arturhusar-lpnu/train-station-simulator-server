    package com.simulation.dtos;

    import java.util.List;

    public record ConfigDto(String startOfWorkingDay, String endOfWorkingDay, int payDecksCount, List<Long> timeStrategyArguments, List<Integer> ticketsStrategyArguments) {
        "08:00", "18:10", 3, [1] ([1,2]), [3] ([1,4])

        // + - - -


    }
