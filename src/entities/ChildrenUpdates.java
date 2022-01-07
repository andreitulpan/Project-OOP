package entities;

import java.util.ArrayList;

public final class ChildrenUpdates {
    private Integer id;
    private final Double newNiceScore;
    private final ArrayList<String> newGiftsPreferences;

    public ChildrenUpdates(final Integer id, final Double newNiceScore,
                           final ArrayList<String> newGiftsPreferences) {
        this.id = id;
        this.newNiceScore = newNiceScore;
        this.newGiftsPreferences = newGiftsPreferences;
    }

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public Double getNewNiceScore() {
        return newNiceScore;
    }

    public ArrayList<String> getNewGiftsPreferences() {
        return newGiftsPreferences;
    }

}
