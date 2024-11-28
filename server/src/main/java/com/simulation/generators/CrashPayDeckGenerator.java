package com.simulation.generators;

import com.simulation.models.PayDeck;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class CrashPayDeckGenerator {
    private final PayDeckSystem payDeckSystem;
    private final double crashProbability;
    private PayDeck crashedPayDeck;
    private ScheduledThreadPoolExecutor crashThread;

    public CrashPayDeckGenerator(PayDeckSystem payDeckSystem, double crashProbability) {
        this.payDeckSystem = payDeckSystem;
        this.crashProbability = crashProbability;
    }

    public void startCrashes() {
        crashThread = new ScheduledThreadPoolExecutor(1);
        crashThread.schedule(this::crashRandomDeck, 0, TimeUnit.SECONDS);
    }

    private void crashRandomDeck() {
        Random rand = new Random();
        if(rand.nextDouble() <= crashProbability) {
            scheduleNextCrash();// startAgain
            return;
        }

        List<PayDeck> workingPayDecks = new ArrayList<>(payDeckSystem.getPayDecks().stream().filter(PayDeck::isWorking).toList());

        if(workingPayDecks.isEmpty() && !crashThread.isShutdown()) {
            crashThread.shutdownNow();
        }

        int randomIndex = rand.nextInt(workingPayDecks.size());

        crashedPayDeck = workingPayDecks.get(randomIndex);
        System.out.println("Crash created");
        workingPayDecks.remove(crashedPayDeck);
        payDeckSystem.interruptTask(crashedPayDeck);

        crashedPayDeck.getClientsQueue().forEach(c -> {
            PayDeck choosedPayDeck = PayDeckChooseSystem.choosePaydeck(workingPayDecks, c);
            choosedPayDeck.addClient(c);
        });

        scheduleNextCrash();
    }

    private void scheduleNextCrash() {
        Random rand = new Random();
        int newCrashTime = rand.nextInt(20, 60) + 100;
        crashThread.schedule(this::crashRandomDeck, newCrashTime, TimeUnit.SECONDS);
    }

    public void stopCrashes() {
        crashThread.shutdownNow();
    }
}