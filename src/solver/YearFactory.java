package solver;

public final class YearFactory {

    /**
     * Creeaza strategia corespunzatoare anului
     * @param solver solver-ul
     * @param year anul in functie de care se alege strategia
     * @return strategia potrivita
     */
    public YearStrategy createStrategy(final Solver solver, final int year) {
        if (year == 0) {
            return new FirstYear(solver, year);
        } else {
            return new AnotherYear(solver, year);
        }
    }
}
