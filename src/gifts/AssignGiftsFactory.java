package gifts;

import entities.AnnualChanges;
import solver.Solver;

import java.util.ArrayList;

public final class AssignGiftsFactory {

    /**
     * Creeaza strategia corespunzatoare anului
     * @param solver solver-ul
     * @param year anul in functie de care se alege strategia
     * @param annualChanges schimbarile anuale
     * @return strategia potrivita
     */
    public AssignGiftsStrategy createStrategy(final Solver solver,
                                              final Integer year,
                                              final ArrayList<AnnualChanges> annualChanges) {
        if (year == -1) {
            return new FirstYearAssign(solver);
        } else {
            return new AnotherYearAssign(solver, annualChanges.get(year).getStrategy());
        }
    }
}
