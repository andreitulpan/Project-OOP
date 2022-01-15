package average;

import entities.Child;
import solver.Solver;

import java.util.ArrayList;

public final class AverageFactory {

    /**
     * Creeaza strategia corespunzatoare anului
     * @param solver solver-ul
     * @param year anul in functie de care se alege strategia
     * @param  childrenToRemove salveaza copiii ce trebuie stersi
     * @return strategia potrivita
     */
    public AverageStrategy createStrategy(final Solver solver,
                                                 final Integer year,
                                                 final ArrayList<Child> childrenToRemove) {
        if (year == -1) {
            return new FirstYearAverage(solver, childrenToRemove);
        } else {
            return new AnotherYearAverage(solver, childrenToRemove);
        }
    }
}
