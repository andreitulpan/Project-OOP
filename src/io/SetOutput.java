package io;

import com.fasterxml.jackson.databind.ObjectWriter;
import entities.Child;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;

public final class SetOutput {

    public static void SetData(JSONArray outputArray, ObjectWriter writer, String file) {
        try {
            JSONObject annualChildren = new JSONObject();
            annualChildren.put("annualChildren", outputArray);
            writer.writeValue(Paths.get(file).toFile(), annualChildren);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
