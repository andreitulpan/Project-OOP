package solver;

import entities.Gift;

import java.util.ArrayList;
import java.util.Comparator;

public final class PerfectGift {
    public static Gift Find(String category, ArrayList<Gift> gifts) {
        ArrayList<Gift> giftsInCategory = new ArrayList<>();

        for (Gift gift: gifts) {
            if (gift.getCategory().equals(category))
                giftsInCategory.add(gift);
        }

        // Se sorteaza lista dupa pret
        giftsInCategory.sort(Comparator.comparingDouble(Gift::getPrice));

        return giftsInCategory.get(0);
    }
}
