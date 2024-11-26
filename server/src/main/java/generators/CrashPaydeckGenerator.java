package generators;

import models.PayDeck;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class CrashPaydeckGenerator {
    private PayDeckSystem payDeckSystem;
    private double crashProbability;
    private ScheduledThreadPoolExecutor scheduler;

    public void startCrashes() {
        scheduler = new ScheduledThreadPoolExecutor(1);

        scheduler.scheduleAtFixedRate(this::crashRandomDeck, 0, 120, TimeUnit.SECONDS);
    }

    private void crashRandomDeck() {
        Random rand = new Random();
        if(rand.nextDouble() <= crashProbability) {
           return;
        }

        List<PayDeck> workingPayDecks = payDeckSystem.getPayDecks().stream().filter(PayDeck::isWorking).toList();

        if(workingPayDecks.isEmpty() && scheduler != null && !scheduler.isShutdown()) {
            scheduler.shutdown();
        }

        int randomIndex = rand.nextInt(workingPayDecks.size());

        PayDeck payDeckToCrush = workingPayDecks.get(randomIndex);
        payDeckSystem.interruptTask(payDeckToCrush.getId());
        payDeckToCrush.getClientsQueue().forEach(c -> PayDeckChooseSystem.choosePaydeck(workingPayDecks, c));
    }
}
