package io;

import entities.Child;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

public final class SetOutput {

    public static void SetData(JSONArray outputArray, String file) {
        try (FileWriter outputFile = new FileWriter(file)) {
            JSONObject annualChildren = new JSONObject();
            annualChildren.put("annualChildren", outputArray);
            outputFile.write(annualChildren.toJSONString());
            outputFile.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
