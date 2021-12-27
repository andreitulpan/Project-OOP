package entities;

import java.util.ArrayList;

public class ChildrenUpdates {
    private Integer id;
    private Double newNiceScore;
    private ArrayList<String> newGiftsPreferences;

    public ChildrenUpdates(Integer id, Double newNiceScore, ArrayList<String> newGiftsPreferences) {
        this.id = id;
        this.newNiceScore = newNiceScore;
        this.newGiftsPreferences = newGiftsPreferences;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getNewNiceScore() {
        return newNiceScore;
    }

    public void setNewNiceScore(Double newNiceScore) {
        this.newNiceScore = newNiceScore;
    }

    public ArrayList<String> getNewGiftsPreferences() {
        return newGiftsPreferences;
    }

    public void setNewGiftsPreferences(ArrayList<String> newGiftsPreferences) {
        this.newGiftsPreferences = newGiftsPreferences;
    }

    @Override
    public String toString() {
        return "ChildrenUpdates{" +
                "id=" + id +
                ", newNiceScore=" + newNiceScore +
                ", newGiftsPreferences=" + newGiftsPreferences +
                '}';
    }
}
