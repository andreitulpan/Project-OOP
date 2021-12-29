package solver;

import entities.AnnualChanges;
import entities.Child;
import entities.ChildrenUpdates;
import entities.Gift;
import io.ChildOutput;
import org.json.simple.JSONArray;

import java.util.ArrayList;

public final class AnotherYear {
    public static void Solver(Solver solver, int year, JSONArray outputArray) {
        ArrayList<Child> childrenToRemove = new ArrayList<>();

        // Ajung la index-ul necesar din lista;
//        year -= 1;

        // Salvez suma scorurilor in variabila aceasta
        double scoreSum = 0;

        // Adaug copiii noi daca au sub 18 ani
        for (Child newChild: solver.getAnnualChanges().get(year).getNewChildren()) {
            if (newChild.getAge() < 18)
                solver.getChildren().add(newChild);
        }

        for (Child child: solver.getChildren()) {
            // Se incrementeaza varsta cu 1
            child.setAge(child.getAge() + 1);

            if (child.getAge() < 18) {
                for (ChildrenUpdates childrenUpdates : solver.getAnnualChanges().get(year).getChildrenUpdates()) {
                    if (childrenUpdates.getId().equals(child.getId())) {
                        if (childrenUpdates.getNewNiceScore() != null) {
                            child.setNiceScore(childrenUpdates.getNewNiceScore());
//                            child.getScoreList().add(child.getNiceScore());
                        }
                        if (childrenUpdates.getNewGiftsPreferences().size() > 0) {
                            for (String category : childrenUpdates.getNewGiftsPreferences()) {
                                child.getGiftsPreferences().remove(category);
                                child.getGiftsPreferences().add(0, category);
                            }
                        }
                        break;
                    }
                }

                // Adaug niceScore-ul in lista
                child.getNiceScoreHistory().add(child.getNiceScore());

                // Calculez averageScore-ul pentru fiecare copil
                if (child.getAge() < 5) {
                    child.setAverageScore(10.0);
                } else if (child.getAge() >= 5 && child.getAge() < 12) {
                    child.setAverageScore(child.getNiceScore());
                } else if (child.getAge() >= 12) {
                    child.setAverageScore(child.getNiceScore());
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
            ChildOutput.SetChild(child, outputArray);
        }
    }
}
