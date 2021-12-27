package solver;

import entities.AnnualChanges;
import entities.Child;
import entities.Gift;
import io.GetInput;

import java.util.ArrayList;

public class Solver {
    private Integer numberOfYears;
    private Double santaBudget;
    private ArrayList<Child> children;
    private ArrayList<Gift> gifts;
    private ArrayList<AnnualChanges> annualChanges;

    public Solver() {
        this.numberOfYears = 0;
        this.santaBudget = (double) 0;
        this.children = new ArrayList<>();
        this.gifts = new ArrayList<>();
        this.annualChanges = new ArrayList<>();
    }

    public Integer getNumberOfYears() {
        return numberOfYears;
    }

    public void setNumberOfYears(Integer numberOfYears) {
        this.numberOfYears = numberOfYears;
    }

    public Double getSantaBudget() {
        return santaBudget;
    }

    public void setSantaBudget(Double santaBudget) {
        this.santaBudget = santaBudget;
    }

    public ArrayList<Child> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<Child> children) {
        this.children = children;
    }

    public ArrayList<Gift> getGifts() {
        return gifts;
    }

    public void setGifts(ArrayList<Gift> gifts) {
        this.gifts = gifts;
    }

    public ArrayList<AnnualChanges> getAnnualChanges() {
        return annualChanges;
    }

    public void setAnnualChanges(ArrayList<AnnualChanges> annualChanges) {
        this.annualChanges = annualChanges;
    }

    @Override
    public String toString() {
        return "Solver{" +
                "numberOfYears=" + numberOfYears +
                ", santaBudget=" + santaBudget +
                ", children=" + children +
                ", gifts=" + gifts +
                ", annualChanges=" + annualChanges +
                '}';
    }
}
