package generators;

import dtos.ConfigDto;
import event_dispather.EventLogger.EventLogger;
import event_dispather.WebNotifier.WebNotifier;
import exceptions.InvalidArgumentException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import models.StationRoom;
import models.WorkingTimer;
import strategy.tickets.FixedTicketsStrategy;
import strategy.tickets.RandomTicketsStrategy;
import strategy.tickets.TicketsGenerationStrategy;
import strategy.time.FixedTimeStrategy;
import strategy.time.RandomTimeStrategy;
import strategy.time.TimeGenerationStrategy;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Timer;


@Getter
public class TicketSystemConfig {
    private final PayDeckSystem payDeckSystem;
    private final ClientGenerator clientGenerator;
    private final long durationOfDay;
    private final LocalTime startOfWorkingDay;
    private final LocalTime endOfWorkingDay;
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
    }
}
