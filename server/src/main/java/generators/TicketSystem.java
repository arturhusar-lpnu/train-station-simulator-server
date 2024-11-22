package generators;

import events.EventDispatcher;
import lombok.Getter;
import lombok.Setter;
import models.PayDeck;
import models.StationRoom;

import java.time.LocalTime;
import java.util.List;
import java.util.Timer;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

//================================================================================
// Code smells and smells good ;)
// ril
//================================================================================

@Getter
@Setter
public class TicketSystem {
    private PayDeckSystem payDeckSystem;
    private ClientGenerator clientGenerator;
    private StationRoom roomMap;
    private Timer timer;
    private LocalTime startOfWorkingDay;
    private LocalTime endOfWorkingDay;
    private EventDispatcher eventDispatcher;
    private ClientMoveSystem clientMoveSystem;
    private static TicketSystem instance;
    private static ReentrantLock lock = new ReentrantLock();
    private AtomicBoolean isFirstTime = new AtomicBoolean(true);

    private TicketSystem(TicketSystemConfig config) {
        this.payDeckSystem = config.getPayDeckSystem();
        this.clientGenerator = config.getClientGenerator();
        this.roomMap = config.getRoomMap();
        this.timer = config.getTimer();
        this.startOfWorkingDay = config.getStartOfWorkingDay();
        this.endOfWorkingDay = config.getEndOfWorkingDay();
        this.eventDispatcher = config.getEventDispatcher();
        this.clientMoveSystem = config.getClientMoveSystem();
    }

    public static TicketSystem getInstance() {
        lock.lock();

        if (instance == null) {
            throw new IllegalStateException("TicketSystem is not initialized. Use getInstance with parameter first.");
        }

        lock.unlock();
        return instance;
    }

    public static TicketSystem getInstance(TicketSystemConfig config) {
        lock.lock();

        if (instance == null) {
            instance = new TicketSystem(config);
        }

        lock.unlock();
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
