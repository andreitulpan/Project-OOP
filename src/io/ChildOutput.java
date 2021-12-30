package io;

import entities.Child;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public final class ChildOutput {

    public static void SetChild(Child child, JSONArray childrenArray) {
        childrenArray.add(new Child(child));
    }

    public static void SetData(JSONArray childrenArray, JSONArray outputArray) {
        JSONObject childrenObj = new JSONObject();
        childrenObj.put("children", childrenArray);
        outputArray.add(childrenObj);
    }
}
