package io;

import entities.Child;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public final class ChildOutput {

    public static void SetChild(Child child, JSONArray outputArray) {
        JSONObject childObj = new JSONObject();
        childObj.put("id", child.getId());
        childObj.put("lastName", child.getLastName());
        childObj.put("firstName", child.getFirstName());
        childObj.put("city", child.getCity());
        childObj.put("age", child.getAge());
        childObj.put("giftsPreferences", child.getGiftsPreferences()); // list
        childObj.put("averageScore", child.getAverageScore());
        childObj.put("niceScoreHistory", child.getNiceScoreHistory()); // list
        childObj.put("assignedBudget", child.getAssignedBudget());
        childObj.put("receivedGifts", child.getReceivedGifts()); // list
        outputArray.add(childObj);
    }
}
