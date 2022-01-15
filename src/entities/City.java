package entities;

import java.util.ArrayList;
import java.util.Comparator;

import static common.Constants.AGE_18;

public final class City {
    private final String name;
    private final ArrayList<Child> child;
    private Double niceScore;

    public City(final String name) {
        this.name = name;
        this.niceScore = (double) 0;
        this.child = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public Double getNiceScore() {
        return niceScore;
    }

    public void setNiceScore(final Double niceScore) {
        this.niceScore = niceScore;
    }

    public ArrayList<Child> getChild() {
        return child;
    }

    /**
     * Calculeaza niceScore-ul orasului
     *
     */
    public void calculateNiceScore() {
        this.getChild().sort(Comparator.comparingDouble(Child::getId));
        double average = 0;

        ArrayList<Child> toRemove = new ArrayList<>();
        for (Child children: this.getChild()) {

            if (children.getAge() <= AGE_18) {
                average += children.getAverageScore();
            } else {
                toRemove.add(children);
            }

        }

        this.getChild().removeAll(toRemove);

        average /= this.getChild().size();
        this.setNiceScore(average);
    }
}
