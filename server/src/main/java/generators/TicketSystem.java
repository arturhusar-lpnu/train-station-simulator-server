package generators;

import events.EventDispatcher;
import lombok.Getter;
import lombok.Setter;
import models.PayDeck;
import models.StationRoom;

import java.time.LocalTime;
import java.util.List;
import java.util.Timer;

//================================================================================
// Code smells
// ril
//================================================================================

@Getter
@Setter
public class TicketSystem {
    private PayDeckSystem payDeckSystem;
    private ClientGenerator clientGenerator;
    private List<PayDeck> payDecks;
    private StationRoom roomMap;
    private Timer timer;
    private LocalTime startOfWorkingDay;
    private LocalTime endOfWorkingDay;
    private EventDispatcher eventDispatcher;
    private ClientMoveSystem clientMoveSystem;
    private static TicketSystem instance;

    private TicketSystem(PayDeckSystem payDeckSystem, ClientGenerator clientGenerator,
                         List<PayDeck> payDecks, StationRoom roomMap, Timer timer,
                         LocalTime startOfWorkingDay, LocalTime endOfWorkingDay,EventDispatcher eventDispatcher, ClientMoveSystem clientMoveSystem) {
        this.payDeckSystem = payDeckSystem;
        this.clientGenerator = clientGenerator;
        this.payDecks = payDecks;
        this.roomMap = roomMap;
        this.timer = timer;
        this.startOfWorkingDay = startOfWorkingDay;
        this.endOfWorkingDay = endOfWorkingDay;
        this.eventDispatcher = eventDispatcher;
        this.clientMoveSystem = clientMoveSystem;
    }

    public static TicketSystem getInstance() {
        if (instance == null) {
            throw new IllegalStateException("TicketSystem is not initialized. Use getInstance with parameters first.");
        }
        return instance;
    }

    public static TicketSystem getInstance(PayDeckSystem payDeckSystem, ClientGenerator clientGenerator,
                                           List<PayDeck> payDecks, StationRoom roomMap, Timer timer,
                                           LocalTime startOfWorkingDay, LocalTime endOfWorkingDay, EventDispatcher eventDispatcher, ClientMoveSystem clientMoveSystem) {
        if (instance == null) {
            instance = new TicketSystem(payDeckSystem, clientGenerator, payDecks, roomMap, timer,
                    startOfWorkingDay, endOfWorkingDay, eventDispatcher, clientMoveSystem);
        }
        return instance;
    }
    public void startClientGenerator() {

    }

    public void startPayDecks() {

    }

    public void startTimer() {

    }

    public void stopTimer() { //рома писав що можна додати пул потоків

    }

    public void startSystem() {
        if(instance == null) {
            throw new IllegalStateException("TicketSystem is not initialized. Use getInstance with parameters first.");
        }
        startTimer();
        startClientGenerator();
        startPayDecks();
        stopTimer();
    }
}
