package main;

import fileio.SetOutput;
import solver.Solver;
import checker.Checker;
import fileio.GetInput;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static common.Constants.FILE_EXTENSION;
import static common.Constants.FIRST_TEST;
import static common.Constants.INPUT_PATH;
import static common.Constants.OUTPUT_DIR;
import static common.Constants.OUTPUT_PATH;
import static common.Constants.TESTS_NUMBER;

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

        // Se creeaza directorul pentru output
        try {
            Files.createDirectories(Path.of(OUTPUT_DIR));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Parcurg fiecare test si execut simularea pe el
        for (int testNumber = FIRST_TEST; testNumber <= TESTS_NUMBER; testNumber++) {
            // Primeste instanta pentru Solver
            Solver solver = Solver.getInstance();

            // Primeste input-ul
            GetInput.getData(solver, INPUT_PATH + testNumber + FILE_EXTENSION);

            // Executa simularea
            solver.simulate();

            // Scrie output-ul
            SetOutput.setData(solver, OUTPUT_PATH + testNumber + FILE_EXTENSION);
        }

        // Se apeleaza checker-ul
        Checker.calculateScore();
    }
}
