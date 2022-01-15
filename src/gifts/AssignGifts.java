package gifts;

import entities.Child;
import entities.Gift;

import java.util.ArrayList;

import static common.Constants.CHEAP;
import static common.Constants.NORMAL;
import static common.Constants.YELLOW;

public final class AssignGifts {

    private AssignGifts() {
        ///constructor for checkstyle
    }

    /**
     * Ofera cadouri copilului daca indeplineste cerintele
     *
     * @param child copilul care primeste caoduri
     * @param gifts lista de cadouri a mosului
     */
    public static void assign(final Child child, final ArrayList<Gift> gifts) {
        double sumReceivedGifts = 0;

        // Parcurge fiecare categorie din lista de preferinte
        for (String category: child.getGiftsPreferences()) {

            // Alege cadoul perfect pentru copil din categoria respectiva
            Gift gift = PerfectGift.find(category, gifts, NORMAL);

            // Daca exista cadoul si se incadreaza in
            // bugetul ramas, copilul primeste cadoul
            if (gift != null && child.getAssignedBudget()
                    - sumReceivedGifts - gift.getPrice() >= 0) {
                sumReceivedGifts += gift.getPrice();
                child.getReceivedGifts().add(gift);
                // Scad cantitatea ramasa a cadoului cu 1
                gift.setQuantity(gift.getQuantity() - 1);
            }
        }

        // Daca copilul nu a primit niciun cadou si are elful
        // galben va primi cel mai ieftin cadou din categoria dorita
        if (!child.getGiftsPreferences().isEmpty()
                && child.getReceivedGifts().isEmpty()
                && child.getElf().equals(YELLOW)) {
            Gift gift = PerfectGift.find(child.getGiftsPreferences().get(0), gifts, CHEAP);
            if (gift != null && gift.getQuantity() > 0) {
                child.getReceivedGifts().add(gift);
                gift.setQuantity(gift.getQuantity() - 1);
            }
        }
    }
}
