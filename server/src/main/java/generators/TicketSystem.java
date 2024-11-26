package generators;

import event_dispather.EventLogger.EventLogger;
import services.SimulationService;
import lombok.Getter;
import lombok.Setter;

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
    private SimulationService simulationService;
    private CrashPaydeckGenerator crashPaydeckGenerator;
    private EventLogger eventLogger;
    private static TicketSystem instance;
    private static ReentrantLock lock = new ReentrantLock();

    private TicketSystem(TicketSystemConfig config) {
        this.payDeckSystem = config.getPayDeckSystem();
        this.clientGenerator = config.getClientGenerator();
        this.eventLogger = config.getEventLogger();
        crashPaydeckGenerator = config.getCrashGenerator();
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
        clientGenerator.startGenerateClients();
    }

    public void stopClientGenerator() {
        clientGenerator.stopGenerateClients();
    }

    public void startCrashGeneration() { crashPaydeckGenerator.startCrashes();}


    public void startSystem() {
        if(instance == null) {
            throw new IllegalStateException("TicketSystem is not initialized. Use getInstance with parameters first.");
        }
        //Client
        startClientGenerator();

        //Crashes
        startCrashGeneration();
    }
    public void stopSystem() {
        payDeckSystem.shutdown();
        stopClientGenerator();
    }
}
