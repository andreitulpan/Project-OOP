package fileio;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.json.simple.JSONObject;
import solver.Solver;

import java.nio.file.Paths;

public final class SetOutput {

    private SetOutput() {
        ///constructor for checkstyle
    }

    /**
     * Scrie datele salvate in solver in urma
     * executarii simularii in fisierul de output
     *
     * @param solver solver-ul din care se citest datele
     * @param file numele fisierului pentru output
     */
    @SuppressWarnings("unchecked")
    public static void setData(final Solver solver, final String file) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
        try {
            JSONObject annualChildren = new JSONObject();
            annualChildren.put("annualChildren", solver.getOutputArray());
            writer.writeValue(Paths.get(file).toFile(), annualChildren);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
