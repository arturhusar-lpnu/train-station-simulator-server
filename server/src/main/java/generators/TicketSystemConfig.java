package generators;

import event_dispather.EventLogger.EventLogger;
import event_dispather.WebNotifier.WebNotifier;
import lombok.AllArgsConstructor;
import lombok.Getter;
import models.StationRoom;

import java.time.LocalTime;
import java.util.Timer;

@AllArgsConstructor
@Getter
public class TicketSystemConfig {
    private PayDeckSystem payDeckSystem;
    private ClientGenerator clientGenerator;
    private StationRoom roomMap;
    private Timer timer;
    private LocalTime startOfWorkingDay;
    private LocalTime endOfWorkingDay;
//    private EventDispatcher eventDispatcher;
    private WebNotifier webNotifier;
    private EventLogger eventLogger;
    private ClientMoveSystem clientMoveSystem;
}

// CLIENT DTO
/*  TimeGenerationStrategy
TicketsGenerationStrategy
*   StationMap : private int width;
    private int length;

    private List<Position> entries;
    private List<Position> exits;

    Map created from width and length
    В конструкторі конфігу підключити івенти і їх слухачів, створити payDeckSystem, clientMoveSystem,

* */
