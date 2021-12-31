package solver;

import entities.Child;
import entities.ChildrenUpdates;
import fileio.ChildOutput;
import org.json.simple.JSONArray;

import java.util.ArrayList;
import java.util.Collections;

public class AnotherYear implements YearStrategy {
    private final Solver solver;
    private int year;

    public AnotherYear(Solver solver, int year) {
        this.solver = solver;
        this.year = year;
    }

    public void solver() {
        ArrayList<Child> childrenToRemove = new ArrayList<>();
        JSONArray childrenArray = new JSONArray();

        year -= 1;

        // Se incrementeaza varsta cu 1
        for (Child child: solver.getChildren())
            child.setAge(child.getAge() + 1);

        // Salvez suma scorurilor in variabila aceasta
        double scoreSum = 0;

        // Adaug copiii noi daca au sub 18 ani
        for (Child newChild: solver.getAnnualChanges().get(year).getNewChildren()) {
            if (newChild.getAge() <= 18)
                solver.getChildren().add(newChild);
        }

        for (Child child: solver.getChildren()) {

            // Se reinitializeaza lista de cadouri
            child.setReceivedGifts(new ArrayList<>());

            if (child.getAge() <= 18) {
                for (ChildrenUpdates childrenUpdates : solver.getAnnualChanges().get(year).getChildrenUpdates()) {
                    if (childrenUpdates.getId().equals(child.getId())) {
                        if (childrenUpdates.getNewNiceScore() != -1)
                            child.getNiceScoreHistory().add(childrenUpdates.getNewNiceScore());

                        ArrayList<String> newGiftsPreferences = new ArrayList<>();
                        for (String element: childrenUpdates.getNewGiftsPreferences())
                            if (!newGiftsPreferences.contains(element))
                                newGiftsPreferences.add(element);
                        Collections.reverse(newGiftsPreferences);

                        for (String category: newGiftsPreferences) {
                            child.getGiftsPreferences().remove(category);
                            child.getGiftsPreferences().add(0, category);
                        }
                    }
                }

                // Adaug niceScore-ul in lista
//                child.getNiceScoreHistory().add(child.getNiceScore());

                // Calculez averageScore-ul pentru fiecare copil
                if (child.getAge() < 5) {
                    child.setAverageScore(10.0);
                } else if (child.getAge() >= 5 && child.getAge() < 12) {
                    double averageScore = 0;
                    for (double niceScore: child.getNiceScoreHistory())
                        averageScore += niceScore;
                    averageScore = averageScore / child.getNiceScoreHistory().size();
                    child.setAverageScore(averageScore);
                } else if (child.getAge() >= 12) {
                    double averageScore = 0;
                    int index = 0;
                    for (double niceScore: child.getNiceScoreHistory()) {
                        index++;
                        averageScore += niceScore * index;
                    }
                    averageScore = 2 * averageScore / (index * (index + 1));
                    child.setAverageScore(averageScore);
                }

                // Calculez suma scorurilor
                scoreSum += child.getAverageScore();

            } else {
                // Se adauga copiii peste 18 pentru a fi stersi
                     childrenToRemove.add(child);
            }
        }
        // Se adauga noile cadouri
        solver.getGifts().addAll(solver.getAnnualChanges().get(year).getNewGifts());

        // Se updateaza bugetul mosului
        solver.setSantaBudget(solver.getAnnualChanges().get(year).getNewSantaBudget());

        // Se sterg copiii peste 18 ani
        for (Child child: childrenToRemove)
            solver.getChildren().remove(child);

        double budgetUnit = solver.getSantaBudget() / scoreSum;

        // Calculez bugetul pentru fiecare copil si ii asignez cadourile
        for (Child child: solver.getChildren()) {
            child.setAssignedBudget(child.getAverageScore() * budgetUnit);
            AssignGifts.Assign(child, solver.getGifts());
//            outputArray.add(child);
            ChildOutput.SetChild(child, childrenArray);
        }

        ChildOutput.SetData(childrenArray, solver.getOutputArray());
    }
}
