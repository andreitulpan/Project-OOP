package solver;

import entities.Child;
import entities.Gift;

import java.util.ArrayList;

public final class AssignGifts {
    public static void Assign(Child child, ArrayList<Gift> gifts) {
        for (String category: child.getGiftsPreferences()) {
            Gift gift = PerfectGift.Find(category, gifts);
            if (child.getAssignedBudget() - gift.getPrice() >= 0)
                child.getReceivedGifts().add(gift);
        }
    }
}
