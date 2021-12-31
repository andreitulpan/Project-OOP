package solver;

import org.json.simple.JSONArray;

import static common.Constants.ANOTHER_YEAR_STRATEGY;
import static common.Constants.FIRST_YEAR_STRATEGY;

public class YearFactory {
    public YearStrategy createStrategy(Solver solver, int year) {
        if (year == 0)
            return new FirstYear(solver, year);
        else
            return new AnotherYear(solver, year);
    }
}
