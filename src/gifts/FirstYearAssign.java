package gifts;

import entities.Child;
import solver.Solver;

public final class FirstYearAssign implements AssignGiftsStrategy {
    private final Solver solver;

    public FirstYearAssign(final Solver solver) {
        this.solver = solver;
    }

    @Override
    public void solver() {
        for (Child children : solver.getChildren()) {
            AssignGifts.assign(children, solver.getGifts());
        }
    }
}
