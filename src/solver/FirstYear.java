package solver;

import entities.Child;
import fileio.ChildOutput;
import org.json.simple.JSONArray;

import java.util.ArrayList;

import static common.Constants.AGE_12;
import static common.Constants.AGE_18;
import static common.Constants.AGE_5;
import static common.Constants.GOOD_CHILD_SCORE;

public final class FirstYear implements YearStrategy {
    private final Solver solver;

    public FirstYear(final Solver solver, final int year) {
        this.solver = solver;
    }

    /**
     * Executa simularea pentru primul an
     */
    public void solver() {
        ArrayList<Child> childrenToRemove = new ArrayList<>();
        JSONArray childrenArray = new JSONArray();

        // Salvez suma scorurilor in variabila aceasta
        double scoreSum = 0;

        for (Child child: solver.getChildren()) {

            // Calculez averageScore-ul pentru fiecare copil
            if (child.getAge() < AGE_5) {
                child.setAverageScore(GOOD_CHILD_SCORE);
            } else if (child.getAge() < AGE_12) {
                child.setAverageScore(child.getNiceScoreHistory().get(0));
            } else if (child.getAge() >= AGE_12 && child.getAge() <= AGE_18) {
                child.setAverageScore(child.getNiceScoreHistory().get(0));
            } else {
                childrenToRemove.add(child);
            }

            // Calculez suma scorurilor
            if (child.getAverageScore() != 0) {
                scoreSum += child.getAverageScore();
            }

        }

        // Sterg copiii care au 18 ani sau mai mult
        for (Child child: childrenToRemove) {
            solver.getChildren().remove(child);
        }

        // Calculez budgetUnit-ul
        double budgetUnit = solver.getSantaBudget() / scoreSum;

        // Calculez bugetul pentru fiecare copil si ii asignez cadourile
        for (Child child: solver.getChildren()) {
            child.setAssignedBudget(child.getAverageScore() * budgetUnit);
            AssignGifts.assign(child, solver.getGifts());
            ChildOutput.setChild(child, childrenArray);
        }

        // Se transmit datele catre output
        ChildOutput.setData(childrenArray, solver.getOutputArray());
    }
}
