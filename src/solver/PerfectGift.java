package solver;

import entities.Gift;

import java.util.ArrayList;
import java.util.Comparator;

public final class PerfectGift {

    private PerfectGift() {
        ///constructor for checkstyle
    }

    /**
     * Intoarce cadoul perfect (cel mai
     * ieftin) dintr-o anumita categorie
     *
     * @param category categoria din care trebuie sa faca parte
     * @param gifts lista de cadouri a mosului
     * @return cadoul perfect
     */
    public static Gift find(final String category, final ArrayList<Gift> gifts) {
        ArrayList<Gift> giftsInCategory = new ArrayList<>();

        // Salveaza intr-o lista toate cadourile in categoria primita
        for (Gift gift: gifts) {
            if (gift.getCategory().equals(category)) {
                giftsInCategory.add(gift);
            }
        }

        // Se sorteaza lista dupa pret si se intoarce cel mai ieftin cadou
        giftsInCategory.sort(Comparator.comparingDouble(Gift::getPrice));
        if (!giftsInCategory.isEmpty()) {
            return giftsInCategory.get(0);
        }
        return null;
    }
}
