package main;

import solver.Solver;
import checker.Checker;
import entities.AnnualChanges;
import entities.Child;
import entities.Gift;
import io.GetInput;

/**
 * Class used to run the code
 */
public final class Main {

    private Main() {
        ///constructor for checkstyle
    }
    /**
     * This method is used to call the checker which calculates the score
     * @param args
     *          the arguments used to call the main method
     */
    public static void main(final String[] args) {
//        Checker.calculateScore();
        Solver solver = new Solver();
        GetInput.getData(solver, "tests/test1.json");

        // DEBUGGING
        System.out.println(solver.getNumberOfYears());
        System.out.println(solver.getSantaBudget());

        for (Child child: solver.getChildren()) {
            System.out.println(child);
        }

        for (Gift gift: solver.getGifts()) {
            System.out.println(gift);
        }

        for (AnnualChanges annualChange: solver.getAnnualChanges()) {
            System.out.println(annualChange);
        }
    }
}
