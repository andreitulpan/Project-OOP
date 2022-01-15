package solver;

import common.Utils;
import entities.AnnualChanges;
import entities.Child;
import entities.City;
import entities.Gift;
import org.json.simple.JSONArray;

import java.util.ArrayList;

import static common.Constants.BLACK;
import static common.Constants.ONE_HUNDRED;
import static common.Constants.PERCENT_30;
import static common.Constants.PINK;

public final class Solver {
    private Integer numberOfYears;
    private Double santaBudget;
    private ArrayList<Child> children;
    private ArrayList<Gift> gifts;
    private ArrayList<AnnualChanges> annualChanges;
    private static Solver instance;
    private JSONArray outputArray;
    private ArrayList<City> cities;

    private Solver() {
        ///constructor for checkstyle
    }

    /**
     * Intoarce o instanta noua daca nu exista deja
     * una sau creeaza o instanta noua in caz contrar
     *
     * @return instanta obiectului
     */
    public static Solver getInstance() {
        if (instance == null) {
            instance = new Solver();
        }
        return instance;
    }

    /**
     * Initializeaza parametrii obiectului
     */
    public void initData() {
        this.numberOfYears = 0;
        this.santaBudget = (double) 0;
        this.children = new ArrayList<>();
        this.gifts = new ArrayList<>();
        this.annualChanges = new ArrayList<>();
        this.outputArray = new JSONArray();
        this.cities = new ArrayList<>();
    }

    /**
     * Se executa simularea
     */
    public void simulate() {

        // Se parcurge fiecare an primit si se executa simularea
        for (int year = -1; year < numberOfYears; year++) {
            Simulation.simulate(this, year, annualChanges);
        }
    }

    /**
     * Cauta orasul in lista de orase a solver-ului
     *
     * @param name numele orasului cautat
     * @return orasul cautat daca s-a gasit sau null
     */
    public City findCity(final String name) {
        for (City city: cities) {
            if (city.getName().equals(name)) {
                return city;
            }
        }
        return null;
    }

    /**
     * Realizeaza schimbarile anuale
     *
     * @param year anul pentru care se fac schimbarile
     */
    public void newYear(final Integer year) {

        // Se incrementeaza varsta cu 1
        for (Child child: this.getChildren()) {
            child.setAge(child.getAge() + 1);
        }

        Utils.updateChildList(this, year);

        Utils.updateChild(this, year);

        Utils.updateGiftsAndBudget(this, year);
    }

    /**
     * Calculeaza budgetUnit-ul
     *
     * @return budgetUnit-ul
     */
    public double calculateBudgetUnit() {
        double scoreSum = 0;

        for (Child childrenObj: this.getChildren()) {
            scoreSum += childrenObj.getAverageScore();
        }

        return this.getSantaBudget() / scoreSum;
    }

    /**
     * Calculeaza bugetul pentru fiecare copil
     */
    public void calculateChildBudget() {
        double budgetUnit = calculateBudgetUnit();

        for (Child child: this.getChildren()) {
            double budget = child.getAverageScore() * budgetUnit;

            if (child.getElf().equals(BLACK)) {
                budget -= budget * PERCENT_30 / ONE_HUNDRED;
            } else if (child.getElf().equals(PINK)) {
                budget += budget * PERCENT_30 / ONE_HUNDRED;
            }

            child.setAssignedBudget(budget);
        }
    }

    public Integer getNumberOfYears() {
        return numberOfYears;
    }

    public void setNumberOfYears(final Integer numberOfYears) {
        this.numberOfYears = numberOfYears;
    }

    public Double getSantaBudget() {
        return santaBudget;
    }

    public void setSantaBudget(final Double santaBudget) {
        this.santaBudget = santaBudget;
    }

    public ArrayList<Child> getChildren() {
        return children;
    }

    public void setChildren(final ArrayList<Child> children) {
        this.children = children;
    }

    public ArrayList<Gift> getGifts() {
        return gifts;
    }

    public void setGifts(final ArrayList<Gift> gifts) {
        this.gifts = gifts;
    }

    public ArrayList<AnnualChanges> getAnnualChanges() {
        return annualChanges;
    }

    public void setAnnualChanges(final ArrayList<AnnualChanges> annualChanges) {
        this.annualChanges = annualChanges;
    }

    public JSONArray getOutputArray() {
        return outputArray;
    }

    public ArrayList<City> getCities() {
        return cities;
    }
}
