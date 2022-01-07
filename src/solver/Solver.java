package solver;

import entities.AnnualChanges;
import entities.Child;
import entities.Gift;
import org.json.simple.JSONArray;

import java.util.ArrayList;

public final class Solver {
    private Integer numberOfYears;
    private Double santaBudget;
    private ArrayList<Child> children;
    private ArrayList<Gift> gifts;
    private ArrayList<AnnualChanges> annualChanges;
    private static Solver instance;
    private JSONArray outputArray;

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
    }

    /**
     * Executa simularea
     */
    public void simulate() {

        // Se creeaza o fabrica de strategii
        YearFactory yearFactory = new YearFactory();

        // Se parcurge fiecare an primit ca input
        for (int i = 0; i <= numberOfYears; i++) {
            // Se creeaza strategia corespunzatoare
            YearStrategy yearStrategy = yearFactory.createStrategy(this, i);
            // Se executa simularea
            yearStrategy.solver();
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

}
