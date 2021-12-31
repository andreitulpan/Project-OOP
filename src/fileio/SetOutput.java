package fileio;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import solver.Solver;

import java.nio.file.Paths;

public final class SetOutput {

    public static void SetData(Solver solver, String file) {
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
