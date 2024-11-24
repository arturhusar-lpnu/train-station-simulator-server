package generators;

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
    private EventDispatcher eventDispatcher;
    private ClientMoveSystem clientMoveSystem;
}
