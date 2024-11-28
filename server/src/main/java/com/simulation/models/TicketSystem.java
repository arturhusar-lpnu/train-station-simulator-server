package com.simulation.models;

import com.simulation.event_dispather.EventLogger.EventLogger;
import com.simulation.generators.ClientGenerator;
import com.simulation.generators.CrashPayDeckGenerator;
import com.simulation.generators.PayDeckSystem;
import com.simulation.config.TicketSystemConfig;
import com.simulation.services.SimulationEventsService;
import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.locks.ReentrantLock;

//================================================================================
// Code smells and smells good ;)
// real
//================================================================================

@Getter
@Setter
public class TicketSystem {
    private PayDeckSystem payDeckSystem;
    private ClientGenerator clientGenerator;
    private SimulationEventsService simulationEventsService;
    private CrashPayDeckGenerator crashPaydeckGenerator;
    private EventLogger eventLogger;
    private static TicketSystem instance;
    private static ReentrantLock lock = new ReentrantLock();

    private TicketSystem(TicketSystemConfig config) {
        this.payDeckSystem = config.getPayDeckSystem();
        this.clientGenerator = config.getClientGenerator();
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
    public void startClientGenerator() { clientGenerator.startGenerateClients(); }

    public void stopClientGenerator() { clientGenerator.stopGenerateClients(); }

    public void startCrashGeneration() { crashPaydeckGenerator.startCrashes();}

    public void stopCrashGeneration() { crashPaydeckGenerator.stopCrashes();}

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

        //Client
        stopClientGenerator();

        //Crashes
        stopCrashGeneration();
    }
}
