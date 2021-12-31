package solver;

import entities.AnnualChanges;
import entities.Child;
import entities.Gift;
import org.json.simple.JSONArray;

import java.time.Year;
import java.util.ArrayList;

import static common.Constants.ANOTHER_YEAR_STRATEGY;
import static common.Constants.FIRST_YEAR_STRATEGY;

public class Solver {
    private Integer numberOfYears;
    private Double santaBudget;
    private ArrayList<Child> children;
    private ArrayList<Gift> gifts;
    private ArrayList<AnnualChanges> annualChanges;
    private static Solver instance;
    private JSONArray outputArray;

    private Solver() {}

    public static Solver getInstance() {
        if (instance == null) {
            instance = new Solver();
        }
        return instance;
    }

    public void initData() {
        this.numberOfYears = 0;
        this.santaBudget = (double) 0;
        this.children = new ArrayList<>();
        this.gifts = new ArrayList<>();
        this.annualChanges = new ArrayList<>();
        this.outputArray = new JSONArray();
    }

    public void solve() {
        YearFactory yearFactory = new YearFactory();
        for (int i = 0; i <= numberOfYears; i++) {
            YearStrategy yearStrategy = yearFactory.createStrategy(this, i);
            yearStrategy.solver();
        }
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

    public JSONArray getOutputArray() {
        return outputArray;
    }

    public void setOutputArray(JSONArray outputArray) {
        this.outputArray = outputArray;
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
