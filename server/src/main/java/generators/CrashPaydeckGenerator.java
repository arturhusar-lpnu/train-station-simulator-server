package generators;

import models.PayDeck;

import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class CrashPaydeckGenerator {
    private final PayDeckSystem payDeckSystem;
    private final double crashProbability;
    private PayDeck crashedPayDeck;
    private ScheduledThreadPoolExecutor crashThread;
    private final ScheduledExecutorService recoveryThread = Executors.newSingleThreadScheduledExecutor();

    public CrashPaydeckGenerator(PayDeckSystem payDeckSystem,  double crashProbability) {
        this.payDeckSystem = payDeckSystem;
        this.crashProbability = crashProbability;
    }

    public void startCrashes() {
        crashThread = new ScheduledThreadPoolExecutor(1);
        crashThread.scheduleAtFixedRate(this::crashRandomDeck, 0, 120, TimeUnit.SECONDS);
    }

    private void crashRandomDeck() {
        Random rand = new Random();
        if(rand.nextDouble() <= crashProbability) {
           return;
        }

        List<PayDeck> workingPayDecks = payDeckSystem.getPayDecks().stream().filter(PayDeck::isWorking).toList();

        if(workingPayDecks.isEmpty() && crashThread != null && !crashThread.isShutdown()) {
            crashThread.shutdown();
        }

        int randomIndex = rand.nextInt(workingPayDecks.size());

        crashedPayDeck = workingPayDecks.get(randomIndex);
        payDeckSystem.interruptTask(crashedPayDeck.getId());
        crashedPayDeck.getClientsQueue().forEach(c -> PayDeckChooseSystem.choosePaydeck(workingPayDecks, c));
        int recoveryTime = rand.nextInt(0, 20) + 40;
        recoveryThread.schedule(this::recoverPayDeck, recoveryTime, TimeUnit.SECONDS);
    }

    private void recoverPayDeck() {
        crashedPayDeck.recover();
        payDeckSystem.setRecoveredPayDeck(crashedPayDeck);
    }
    public void stopCrashes() {
        crashThread.shutdown();
    }
}
