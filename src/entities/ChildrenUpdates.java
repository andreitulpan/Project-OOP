package entities;

import java.util.ArrayList;

public final class ChildrenUpdates {
    private Integer id;
    private final Double newNiceScore;
    private final ArrayList<String> newGiftsPreferences;
    private final String newElf;

    public ChildrenUpdates(final Integer id, final Double newNiceScore,
                           final ArrayList<String> newGiftsPreferences,
                           final String newElf) {
        this.id = id;
        this.newNiceScore = newNiceScore;
        this.newGiftsPreferences = newGiftsPreferences;
        this.newElf = newElf;
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

    public String getNewElf() {
        return newElf;
    }
}
