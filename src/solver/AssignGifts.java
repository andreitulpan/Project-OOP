package solver;

import entities.Child;
import entities.Gift;

import java.util.ArrayList;

public final class AssignGifts {
    public static void Assign(Child child, ArrayList<Gift> gifts) {
        double sumReceivedGifts = 0;
        for (String category: child.getGiftsPreferences()) {
            Gift gift = PerfectGift.Find(category, gifts);
            if (gift != null && child.getAssignedBudget() - sumReceivedGifts - gift.getPrice() >= 0) {
                sumReceivedGifts += gift.getPrice();
                child.getReceivedGifts().add(gift);
            }
        }
    }
}
