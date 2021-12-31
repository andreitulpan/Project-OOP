package solver;

import entities.Child;
import fileio.ChildOutput;
import org.json.simple.JSONArray;

import java.util.ArrayList;

public class FirstYear implements YearStrategy {
    private final Solver solver;

    public FirstYear(Solver solver, int year) {
        this.solver = solver;
    }

    public void solver() {
        ArrayList<Child> childrenToRemove = new ArrayList<>();
        JSONArray childrenArray = new JSONArray();

        // Salvez suma scorurilor in variabila aceasta
        double scoreSum = 0;

        for (Child child: solver.getChildren()) {
            // Adaug niceScore-ul in lista
//            child.getNiceScoreHistory().add(child.getNiceScore());

            // Calculez averageScore-ul pentru fiecare copil
            if (child.getAge() < 5) {
                child.setAverageScore(10.0);
            } else if (child.getAge() >= 5 && child.getAge() < 12) {
                child.setAverageScore(child.getNiceScoreHistory().get(0));
            } else if (child.getAge() >= 12 && child.getAge() <= 18) {
                child.setAverageScore(child.getNiceScoreHistory().get(0));
            } else {
                childrenToRemove.add(child);
            }

            // Calculez suma scorurilor
            if (child.getAverageScore() != 0)
                scoreSum += child.getAverageScore();

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
            AssignGifts.Assign(child, solver.getGifts());
//            outputArray.add(new Child(child));
            ChildOutput.SetChild(child, childrenArray);
        }

        ChildOutput.SetData(childrenArray, solver.getOutputArray());
    }
}
