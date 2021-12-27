package io;

import solver.Solver;
import entities.AnnualChanges;
import entities.Child;
import entities.ChildrenUpdates;
import entities.Gift;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public final class GetInput {

    public static void getData(Solver solver, String file) {
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader(file))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);
            JSONObject jsonObject = (JSONObject) obj;

            // NUMBER OF YEARS
            solver.setNumberOfYears(Integer.valueOf(String.valueOf(jsonObject.get("numberOfYears"))));

            // SANTA BUDGET
            solver.setSantaBudget(Double.valueOf(String.valueOf(jsonObject.get("santaBudget"))));

            JSONObject initialDataObj = (JSONObject) jsonObject.get("initialData");

            // CHILDREN
            JSONArray childrenList = (JSONArray) initialDataObj.get("children");
            for (Object object: childrenList) {
                JSONObject childrenObj = (JSONObject)object;
                ArrayList<String> giftsPreferences = new ArrayList<>();
                JSONArray giftsList = (JSONArray) childrenObj.get("giftsPreferences");
                for (Object gift: giftsList) {
                    giftsPreferences.add((String) gift);
                }
                Child child = new Child(Integer.valueOf(String.valueOf(childrenObj.get("id"))),
                        (String) childrenObj.get("lastName"),
                        (String) childrenObj.get("firstName"),
                        Integer.valueOf(String.valueOf(childrenObj.get("age"))),
                        (String) childrenObj.get("city"),
                        Double.valueOf(String.valueOf(childrenObj.get("niceScore"))),
                        giftsPreferences);
                solver.getChildren().add(child);
            }

            // SANTA GIFTS LIST
            JSONArray santaGiftsList = (JSONArray) initialDataObj.get("santaGiftsList");
            for (Object object: santaGiftsList) {
                JSONObject santaGiftsListObj = (JSONObject)object;
                Gift gift = new Gift((String) santaGiftsListObj.get("productName"),
                        Double.valueOf(String.valueOf(santaGiftsListObj.get("price"))),
                        (String) santaGiftsListObj.get("category"));
                solver.getGifts().add(gift);
            }

            // ANNUAL CHANGES
            JSONArray annualChangesList = (JSONArray) jsonObject.get("annualChanges");
            for (Object object: annualChangesList) {
                JSONObject annualChangesObj = (JSONObject)object;

                // NEWGIFTS
                ArrayList<String> newGifts = new ArrayList<>();
                JSONArray newGiftsList = (JSONArray) annualChangesObj.get("newGifts");
                for (Object newGift: newGiftsList) {
                    newGifts.add(String.valueOf(newGift));
                }

                // NEWCHILDREN
                ArrayList<Child> newChildren = new ArrayList<>();
                JSONArray newChildrenList = (JSONArray) annualChangesObj.get("newChildren");
                for (Object newChild: newChildrenList) {
                    JSONObject newChildObj = (JSONObject) newChild;
                    ArrayList<String> newGiftsPreferences = new ArrayList<>();
                    JSONArray giftsList = (JSONArray) newChildObj.get("giftsPreferences");
                    for (Object newGift: giftsList) {
                        newGiftsPreferences.add((String) newGift);
                    }
                    Child newChildToAdd = new Child(Integer.valueOf(String.valueOf(newChildObj.get("id"))),
                            (String) newChildObj.get("lastName"),
                            (String) newChildObj.get("firstName"),
                            Integer.valueOf(String.valueOf(newChildObj.get("age"))),
                            (String) newChildObj.get("city"),
                            Double.valueOf(String.valueOf(newChildObj.get("niceScore"))),
                            newGiftsPreferences);
                    newChildren.add(newChildToAdd);
                }

                // CHILDREN UPDATES
                ArrayList<ChildrenUpdates> childrenUpdates = new ArrayList<>();
                JSONArray childrenUpdatesList = (JSONArray) annualChangesObj.get("childrenUpdates");
                for (Object childrenUpdate: childrenUpdatesList) {
                    JSONObject childrenUpdateObj = (JSONObject) childrenUpdate;
                    ArrayList<String> newGiftsPreferences = new ArrayList<>();
                    JSONArray giftsList = (JSONArray) childrenUpdateObj.get("giftsPreferences");
                    for (Object newGift: giftsList) {
                        newGiftsPreferences.add((String) newGift);
                    }
                    Double newNiceScore = (double) 0;
                    if (childrenUpdateObj.get("newNiceScore") != null)
                        newNiceScore = (Double) childrenUpdateObj.get("newNiceScore");
                    ChildrenUpdates childrenUpdateToAdd = new ChildrenUpdates(
                            Integer.valueOf(String.valueOf(childrenUpdateObj.get("id"))),
                            newNiceScore, newGiftsPreferences);
                    childrenUpdates.add(childrenUpdateToAdd);
                }

                AnnualChanges annualChangesToAdd = new AnnualChanges(
                        Double.valueOf(String.valueOf(annualChangesObj.get("newSantaBudget"))),
                        newGifts, newChildren, childrenUpdates);
                solver.getAnnualChanges().add(annualChangesToAdd);
            }

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}
