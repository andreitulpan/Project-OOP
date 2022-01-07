package solver;

import entities.Child;
import entities.Gift;

import java.util.ArrayList;

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
            Gift gift = PerfectGift.find(category, gifts);

            // Daca exista cadoul si se incadreaza in
            // bugetul ramas, copilul primeste cadoul
            if (gift != null && child.getAssignedBudget()
                    - sumReceivedGifts - gift.getPrice() >= 0) {
                sumReceivedGifts += gift.getPrice();
                child.getReceivedGifts().add(gift);
            }
        }
    }
}
