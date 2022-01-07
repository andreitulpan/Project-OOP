package solver;

import entities.Child;
import entities.ChildrenUpdates;
import fileio.ChildOutput;
import org.json.simple.JSONArray;

import java.util.ArrayList;
import java.util.Collections;

import static common.Constants.AGE_12;
import static common.Constants.AGE_18;
import static common.Constants.AGE_5;
import static common.Constants.GOOD_CHILD_SCORE;

public final class AnotherYear implements YearStrategy {
    private final Solver solver;
    private int year;

    public AnotherYear(final Solver solver, final int year) {
        this.solver = solver;
        this.year = year;
    }

    /**
     * Executa simularea pentru anii diferiti de primul
     */
    public void solver() {
        ArrayList<Child> childrenToRemove = new ArrayList<>();
        JSONArray childrenArray = new JSONArray();

        year -= 1;

        // Se incrementeaza varsta cu 1
        for (Child child: solver.getChildren()) {
            child.setAge(child.getAge() + 1);
        }

        // Salvez suma scorurilor in variabila aceasta
        double scoreSum = 0;

        // Adaug copiii noi daca au sub 18 ani
        for (Child newChild: solver.getAnnualChanges().get(year).getNewChildren()) {
            if (newChild.getAge() <= AGE_18) {
                solver.getChildren().add(newChild);
            }
        }

        for (Child child: solver.getChildren()) {

            // Se reinitializeaza lista de cadouri
            child.setReceivedGifts(new ArrayList<>());

            if (child.getAge() <= AGE_18) {
                ArrayList<ChildrenUpdates> childrenUpdatesList =
                        solver.getAnnualChanges().get(year).getChildrenUpdates();

                // Parcurg lista de update-uri
                for (ChildrenUpdates childrenUpdates : childrenUpdatesList) {
                    // Fac update copilului daca are acelasi ID
                    if (childrenUpdates.getId().equals(child.getId())) {
                        // Adaug noul niceScore in lista daca acesta exista (!= -1)
                        if (childrenUpdates.getNewNiceScore() != -1) {
                            child.getNiceScoreHistory().add(childrenUpdates.getNewNiceScore());
                        }

                        // Salvez noile preferinte intr-o lista
                        ArrayList<String> newGiftsPreferences = new ArrayList<>();
                        for (String element: childrenUpdates.getNewGiftsPreferences()) {
                            if (!newGiftsPreferences.contains(element)) {
                                newGiftsPreferences.add(element);
                            }
                        }
                        Collections.reverse(newGiftsPreferences);

                        // Fac update listei de preferinte a copilului
                        for (String category: newGiftsPreferences) {
                            child.getGiftsPreferences().remove(category);
                            child.getGiftsPreferences().add(0, category);
                        }
                    }
                }

                // Calculez averageScore-ul pentru fiecare copil
                if (child.getAge() < AGE_5) {
                    child.setAverageScore(GOOD_CHILD_SCORE);
                } else if (child.getAge() < AGE_12) {
                    double averageScore = 0;
                    for (double niceScore: child.getNiceScoreHistory()) {
                        averageScore += niceScore;
                    }
                    averageScore = averageScore / child.getNiceScoreHistory().size();
                    child.setAverageScore(averageScore);
                } else {
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
        solver.getGifts().addAll(
                solver.getAnnualChanges().get(year).getNewGifts());

        // Se updateaza bugetul mosului
        solver.setSantaBudget(
                solver.getAnnualChanges().get(year).getNewSantaBudget());

        // Se sterg copiii peste 18 ani
        for (Child child: childrenToRemove) {
            solver.getChildren().remove(child);
        }

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
