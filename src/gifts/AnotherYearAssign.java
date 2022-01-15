package gifts;

import common.Utils;
import entities.Child;
import entities.City;
import solver.Solver;

import java.util.ArrayList;
import java.util.Comparator;

import static common.Constants.ID;
import static common.Constants.NICE_SCORE;
import static common.Constants.NICE_SCORE_CITY;

public final class AnotherYearAssign implements AssignGiftsStrategy {
    private final Solver solver;
    private final String strategy;

    public AnotherYearAssign(final Solver solver, final String strategy) {
        this.solver = solver;
        this.strategy = strategy;
    }

    @Override
    public void solver() {
        ArrayList<Child> childList = new ArrayList<>(solver.getChildren());
        switch (strategy) {
            case ID -> {
                childList.sort(Comparator.comparingDouble(Child::getId));
                for (Child children : childList) {
                    AssignGifts.assign(children, solver.getGifts());
                }
            }
            case NICE_SCORE -> {
                Utils.sortAverage(childList);
                for (Child children : childList) {
                    AssignGifts.assign(children, solver.getGifts());
                }
            }
            case NICE_SCORE_CITY -> {
                for (City city : solver.getCities()) {
                    city.calculateNiceScore();
                }
                Utils.sortCities(solver.getCities());
                for (City city : solver.getCities()) {
                    for (Child children : city.getChild()) {
                        AssignGifts.assign(children, solver.getGifts());
                    }
                }
            }
            default -> { }
        }
    }
}
