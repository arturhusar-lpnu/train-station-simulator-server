package com.simulation.generators;

import com.simulation.dtos.ConfigDto;
import com.simulation.event_dispather.EventLogger.EventLogger;
import com.simulation.exceptions.InvalidArgumentException;
import lombok.Getter;
import com.simulation.strategy.tickets.FixedTicketsStrategy;
import com.simulation.strategy.tickets.RandomTicketsStrategy;
import com.simulation.strategy.tickets.TicketsGenerationStrategy;
import com.simulation.strategy.time.FixedTimeStrategy;
import com.simulation.strategy.time.RandomTimeStrategy;
import com.simulation.strategy.time.TimeGenerationStrategy;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Random;


@Getter
public class TicketSystemConfig {
    private final PayDeckSystem payDeckSystem;
    private final ClientGenerator clientGenerator;
    private final long durationOfDay;
    private final LocalTime startOfWorkingDay;
    private final LocalTime endOfWorkingDay;
    private final CrashPaydeckGenerator crashGenerator;
    private EventLogger eventLogger;

    public TicketSystemConfig(ConfigDto config) throws InvalidArgumentException {
        startOfWorkingDay = LocalTime.parse(config.startOfWorkingDay());
        endOfWorkingDay = LocalTime.parse(config.endOfWorkingDay());
        durationOfDay = Duration.between(startOfWorkingDay, endOfWorkingDay).toMinutes();
        payDeckSystem = new PayDeckSystem(config.payDecksCount());
        TicketsGenerationStrategy ticketsStrategy;
        if(config.ticketsStrategyArguments().size() == 1) {
            ticketsStrategy = new FixedTicketsStrategy(config.ticketsStrategyArguments());
        } else {
            ticketsStrategy = new RandomTicketsStrategy(config.ticketsStrategyArguments());
        }
        TimeGenerationStrategy timeStrategy;

        if(config.timeStrategyArguments().size() == 1) {
            timeStrategy = new FixedTimeStrategy(config.timeStrategyArguments());
        } else {
            timeStrategy = new RandomTimeStrategy(config.timeStrategyArguments());
        }
        PrivilegeGenerator privilegeGenerator = new PrivilegeGenerator(0.3);

        clientGenerator = new ClientGenerator(timeStrategy, privilegeGenerator, ticketsStrategy);
        Random rand = new Random();
        crashGenerator = new CrashPaydeckGenerator(payDeckSystem, 0.25);
    }
}
