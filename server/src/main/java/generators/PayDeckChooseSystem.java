package generators;

import models.PayDeck;
import models.Client;
import models.privileges.PrivilegeType;

import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class PayDeckChooseSystem {

    // "Вроді працює правильно, тільки Бог знає шо викине в найпотрібніший момент"

    /**
     *   Chooses the most convenient paydeck
     *
     *   @param paydecks     the list of available paydecks
     *   @param client       client itself to determine his privilege
     *   @return the chosen paydec, or null in the worst scenario when random god doesn't help
     */

    public static PayDeck choosePaydeck(List<PayDeck> paydecks, Client client) {
        PayDeck bestPayDeck = null;
        int minTime = Integer.MAX_VALUE;

        for (PayDeck paydeck : paydecks) {
            int processingTime = calculateProcessingTime(paydeck, client);

            if (processingTime < minTime) {
                minTime = processingTime;
                bestPayDeck = paydeck;
            }
        }

        if (bestPayDeck != null) {
            bestPayDeck.addClient(client);
        }

        return bestPayDeck;
    }

    private static int calculateProcessingTime(PayDeck paydeck, Client client) {
        int time = 0;
        boolean clientPositioned = false;

        for (Client queuedClient : paydeck.getClientsQueue()) {
            if (!clientPositioned && queuedClient.getPrivilege().ordinal() >= client.getPrivilege().ordinal()) {
                clientPositioned = true; // Клієнт стає перед першими, хто має рівні або нижчі привілеї
                time += client.getTicketsToBuy();
            }
            time += queuedClient.getTicketsToBuy();
        }

        if (!clientPositioned) {
            // Якщо клієнт ще не доданий, то він стане в кінець
            time += client.getTicketsToBuy();
        }

        return time;
    }
}
