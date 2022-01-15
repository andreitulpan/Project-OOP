package solver;

import average.AverageFactory;
import average.AverageStrategy;
import common.Utils;
import entities.AnnualChanges;
import entities.Child;
import fileio.SetOutput;
import gifts.AssignGiftsFactory;
import gifts.AssignGiftsStrategy;
import org.json.simple.JSONArray;

import java.util.ArrayList;
import java.util.Comparator;

public final class Simulation {

    private Simulation() {
        ///constructor for checkstyle
    }
    /**
     * Executa simularea
     */
    public static void simulate(final Solver solver, final int year,
                                final ArrayList<AnnualChanges> annualChanges) {
        ArrayList<Child> childrenToRemove = new ArrayList<>();
        JSONArray childrenArray = new JSONArray();
        solver.getChildren().sort(Comparator.comparingDouble(Child::getId));

        if (year >= 0) {
            solver.newYear(year);
        }

        // Calculez media de cumintenie a fiecarui copil
        AverageFactory averageFactory = new AverageFactory();
        AverageStrategy averageStrategy = averageFactory.createStrategy(
                solver, year, childrenToRemove);

        // Se calculeaza averageScore-ul pentru fiecare copil
        averageStrategy.solver();

        // Se sterg copiii peste 18 ani
        solver.getChildren().removeAll(childrenToRemove);

        // Se calculeaza bugetul pentru fiecare copil
        Utils.calculateChildBudget(solver);

        // Se asigneaza cadourile fiecarui copil
        AssignGiftsFactory assignGiftsFactory = new AssignGiftsFactory();
        AssignGiftsStrategy assignGiftsStrategy = assignGiftsFactory.createStrategy(
                solver, year, annualChanges);
        assignGiftsStrategy.solver();

        // Setez output-ul
        SetOutput.setChildList(solver);
    }
}
