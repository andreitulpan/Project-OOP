package main;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import io.SetOutput;
import org.json.simple.JSONArray;
import solver.Solver;
import checker.Checker;
import entities.AnnualChanges;
import entities.Child;
import entities.Gift;
import io.GetInput;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import static common.Constants.*;

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
        try {
            Files.createDirectories(Path.of(OUTPUT_DIR));
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int testNumber = 1; testNumber <= 25; testNumber++) {
            Solver solver = new Solver();
            GetInput.getData(solver, INPUT_PATH + testNumber + FILE_EXTENSION);
            ObjectMapper mapper = new ObjectMapper();
            ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
            JSONArray outputArray = new JSONArray();
            solver.Solve(outputArray);
            SetOutput.SetData(outputArray, writer, OUTPUT_PATH + testNumber + FILE_EXTENSION);
        }

        Checker.calculateScore();
    }
}
