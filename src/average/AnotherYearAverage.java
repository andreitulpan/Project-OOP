package average;

import entities.Child;
import solver.Solver;

import java.util.ArrayList;

import static common.Constants.AGE_12;
import static common.Constants.AGE_18;
import static common.Constants.AGE_5;
import static common.Constants.GOOD_CHILD_SCORE;
import static common.Constants.ONE_HUNDRED;
import static common.Constants.TEN;

public final class AnotherYearAverage implements AverageStrategy {
    private final Solver solver;
    private final ArrayList<Child> childrenToRemove;

    public AnotherYearAverage(final Solver solver, final ArrayList<Child> childrenToRemove) {
        this.solver = solver;
        this.childrenToRemove = childrenToRemove;
    }

    @Override
    public void solver() {
        for (Child child: solver.getChildren()) {

            // Calculez averageScore-ul pentru fiecare copil
            if (child.getAge() < AGE_5) {
                child.setAverageScore(GOOD_CHILD_SCORE);

            } else if (child.getAge() < AGE_12) {
                double averageScore = 0;

                for (double niceScore: child.getNiceScoreHistory()) {
                    averageScore += niceScore;
                }

                averageScore = averageScore / child.getNiceScoreHistory().size();

                if (child.getNiceScoreBonus() != 0) {
                    averageScore += averageScore * child.getNiceScoreBonus() / ONE_HUNDRED;
                    if (averageScore > TEN) {
                        averageScore = TEN;
                    }
                }

                child.setAverageScore(averageScore);

            } else if (child.getAge() <= AGE_18) {
                double averageScore = 0;
                int index = 0;

                for (double niceScore: child.getNiceScoreHistory()) {
                    index++;
                    averageScore += niceScore * index;
                }

                averageScore = 2 * averageScore / (index * (index + 1));

                if (child.getNiceScoreBonus() != 0) {
                    averageScore += averageScore * child.getNiceScoreBonus() / ONE_HUNDRED;

                    if (averageScore > TEN) {
                        averageScore = TEN;
                    }

                }
                child.setAverageScore(averageScore);

            } else {

                // Se adauga copiii peste 18 pentru a fi stersi
                childrenToRemove.add(child);
            }
        }
    }
}
