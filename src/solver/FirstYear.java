package solver;

import entities.Child;
import entities.Gift;
import io.ChildOutput;
import org.json.simple.JSONArray;

import java.util.ArrayList;

public final class FirstYear {
    public static void Solver(Solver solver, JSONArray outputArray) {
        ArrayList<Child> childrenToRemove = new ArrayList<>();

        // Salvez suma scorurilor in variabila aceasta
        double scoreSum = 0;

        for (Child child: solver.getChildren()) {
            // Adaug niceScore-ul in lista
            child.getNiceScoreHistory().add(child.getNiceScore());

            // Calculez averageScore-ul pentru fiecare copil
            if (child.getAge() < 5) {
                child.setAverageScore(10.0);
            } else if (child.getAge() >= 5 && child.getAge() < 12) {
                child.setAverageScore(child.getNiceScore());
            } else if (child.getAge() >= 12 && child.getAge() < 18) {
                child.setAverageScore(child.getNiceScore());
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
            ChildOutput.SetChild(child, outputArray);
        }
    }
}
