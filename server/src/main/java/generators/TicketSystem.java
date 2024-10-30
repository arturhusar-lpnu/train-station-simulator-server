package generators;

import lombok.Getter;
import lombok.Setter;
import models.PayDeck;
import models.StationRoom;

import java.time.LocalTime;
import java.util.List;
import java.util.Timer;


//================================================================================
// Code smells
//================================================================================

@Getter
@Setter
public class TicketSystem {
    private PayDeck reservedPayDeck;
    private ClientGenerator clientGenerator;
    private List<PayDeck> payDecks;
    private StationRoom roomMap;
    private Timer timer;
    private LocalTime startOfWorkingDay;
    private LocalTime endOfWorkingDay;
    private static TicketSystem instance;

    private TicketSystem(PayDeck reservedPayDeck, ClientGenerator clientGenerator,
                         List<PayDeck> payDecks, StationRoom roomMap, Timer timer,
                         LocalTime startOfWorkingDay, LocalTime endOfWorkingDay) {
        this.reservedPayDeck = reservedPayDeck;
        this.clientGenerator = clientGenerator;
        this.payDecks = payDecks;
        this.roomMap = roomMap;
        this.timer = timer;
        this.startOfWorkingDay = startOfWorkingDay;
        this.endOfWorkingDay = endOfWorkingDay;
    }

    public static TicketSystem getInstance() {
        if (instance == null) {
            throw new IllegalStateException("TicketSystem is not initialized. Use getInstance with parameters first.");
        }
        return instance;
    }

    public static TicketSystem getInstance(PayDeck reservedPayDeck, ClientGenerator clientGenerator,
                                           List<PayDeck> payDecks, StationRoom roomMap, Timer timer,
                                           LocalTime startOfWorkingDay, LocalTime endOfWorkingDay) {
        if (instance == null) {
            instance = new TicketSystem(reservedPayDeck, clientGenerator, payDecks, roomMap, timer,
                    startOfWorkingDay, endOfWorkingDay);
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
