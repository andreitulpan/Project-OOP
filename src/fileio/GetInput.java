package fileio;

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

import static common.Constants.AGE;
import static common.Constants.ANNUAL_CHANGES;
import static common.Constants.CATEGORY;
import static common.Constants.CHILDREN;
import static common.Constants.CHILDREN_UPDATES;
import static common.Constants.CITY;
import static common.Constants.FIRST_NAME;
import static common.Constants.GIFTS_PREFERENCES;
import static common.Constants.ID;
import static common.Constants.INITIAL_DATA;
import static common.Constants.LAST_NAME;
import static common.Constants.NEW_CHILDREN;
import static common.Constants.NEW_GIFTS;
import static common.Constants.NEW_SANTA_BUDGET;
import static common.Constants.NICE_SCORE;
import static common.Constants.NUMBER_OF_YEARS;
import static common.Constants.PRICE;
import static common.Constants.PRODUCT_NAME;
import static common.Constants.SANTA_BUDGET;
import static common.Constants.SANTA_GIFTS_LIST;

public final class GetInput {

    private GetInput() {
        ///constructor for checkstyle
    }

    /**
     * Citeste datele din fisierul de input si le
     * salveaza in campurile corespunzatoare solver-ului
     *
     * @param solver solver-ul in care se salveaza datele
     * @param file numele fisierului de input
     */
    public static void getData(final Solver solver, final String file) {

        // Initializez campurile din solver
        solver.initData();

        // Citesc din fisierul de input
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader(file)) {

            // Citesc toate datele din fisier intr-un obiect JSON
            Object obj = jsonParser.parse(reader);
            JSONObject jsonObject = (JSONObject) obj;

            // NUMBER OF YEARS
            solver.setNumberOfYears(Integer.valueOf(
                    jsonObject.get(NUMBER_OF_YEARS).toString()));

            // SANTA BUDGET
            solver.setSantaBudget(Double.valueOf(
                    jsonObject.get(SANTA_BUDGET).toString()));
            JSONObject initialDataObj = (JSONObject) jsonObject.get(INITIAL_DATA);

            // CHILDREN
            JSONArray childrenList = (JSONArray) initialDataObj.get(CHILDREN);
            // Parcurg lista cu copiii din input
            for (Object object: childrenList) {
                JSONObject childrenObj = (JSONObject) object;
                // Fac o lista de preferinta si adaug preferintele primite la input
                ArrayList<String> giftsPreferences = new ArrayList<>();
                JSONArray giftsList = (JSONArray) childrenObj.get(GIFTS_PREFERENCES);
                for (Object gift: giftsList) {
                    giftsPreferences.add(gift.toString());
                }
                // Creez un copil nou de tip Child cu toate datele primite la input
                Child child = new Child(Integer.valueOf(String.valueOf(childrenObj.get(ID))),
                        childrenObj.get(LAST_NAME).toString(),
                        childrenObj.get(FIRST_NAME).toString(),
                        childrenObj.get(CITY).toString(),
                        Integer.valueOf(childrenObj.get(AGE).toString()),
                        giftsPreferences);
                // Adaug niceScore-ul in lista copilului
                child.getNiceScoreHistory().add(Double.valueOf(
                        childrenObj.get(NICE_SCORE).toString()));
                // Adaug copilul de tip Child in lista
                solver.getChildren().add(child);
            }

            // SANTA GIFTS LIST
            JSONArray santaGiftsList = (JSONArray) initialDataObj.get(SANTA_GIFTS_LIST);
            // Parcurg lista cu caodurile din input
            for (Object object: santaGiftsList) {
                JSONObject santaGiftsListObj = (JSONObject) object;
                // Creez un cadou nou de tip Gift cu toate datele primite la input
                Gift gift = new Gift(santaGiftsListObj.get(PRODUCT_NAME).toString(),
                        Double.valueOf(santaGiftsListObj.get(PRICE).toString()),
                        santaGiftsListObj.get(CATEGORY).toString());
                // Adaug cadoul de tip Gift in lista
                solver.getGifts().add(gift);
            }

            // ANNUAL CHANGES
            JSONArray annualChangesList = (JSONArray) jsonObject.get(ANNUAL_CHANGES);
            // Parcurg lista cu schimbarile anuale din input
            for (Object object: annualChangesList) {
                JSONObject annualChangesObj = (JSONObject) object;

                // NEWGIFTS
                ArrayList<Gift> newGifts = new ArrayList<>();
                JSONArray newGiftsList = (JSONArray) annualChangesObj.get(NEW_GIFTS);
                // Parcurg lista cu noile cadouri din input
                for (Object newGiftObj: newGiftsList) {
                    JSONObject newSantaGift = (JSONObject) newGiftObj;
                    // Creez un cadou nou de tip Gift cu toate datele primite la input
                    Gift newGift = new Gift(newSantaGift.get(PRODUCT_NAME).toString(),
                            Double.valueOf(newSantaGift.get(PRICE).toString()),
                            newSantaGift.get(CATEGORY).toString());
                    // Adaug cadoul de tip Gift in lista
                    newGifts.add(newGift);
                }

                // NEWCHILDREN
                ArrayList<Child> newChildren = new ArrayList<>();
                JSONArray newChildrenList = (JSONArray) annualChangesObj.get(NEW_CHILDREN);
                // Parcurg lista cu noii copii din input
                for (Object newChild: newChildrenList) {
                    JSONObject newChildObj = (JSONObject) newChild;
                    // Fac o lista de preferinta si adaug preferintele primite la input
                    ArrayList<String> newGiftsPreferences = new ArrayList<>();
                    JSONArray giftsList = (JSONArray) newChildObj.get(GIFTS_PREFERENCES);
                    for (Object newGift: giftsList) {
                        newGiftsPreferences.add(newGift.toString());
                    }
                    // Creez un copil nou de tip Child cu toate datele primite la input
                    Child newChildToAdd = new Child(Integer.valueOf(
                            newChildObj.get(ID).toString()),
                            newChildObj.get(LAST_NAME).toString(),
                            newChildObj.get(FIRST_NAME).toString(),
                            newChildObj.get(CITY).toString(),
                            Integer.valueOf(newChildObj.get(AGE).toString()),
                            newGiftsPreferences);
                    // Adaug niceScore-ul in lista copilului
                    newChildToAdd.getNiceScoreHistory().add(Double.valueOf(
                            newChildObj.get(NICE_SCORE).toString()));
                    // Adaug copilul de tip Child in lista
                    newChildren.add(newChildToAdd);
                }

                // CHILDREN UPDATES
                ArrayList<ChildrenUpdates> childrenUpdates = new ArrayList<>();
                JSONArray childrenUpdatesList = (JSONArray) annualChangesObj.get(CHILDREN_UPDATES);
                // Parcurg lista cu update-urile pentru copii
                for (Object childrenUpdate: childrenUpdatesList) {
                    JSONObject childrenUpdateObj = (JSONObject) childrenUpdate;
                    // Fac o lista de preferinta si adaug preferintele primite la input
                    ArrayList<String> newGiftsPreferences = new ArrayList<>();
                    JSONArray giftsList = (JSONArray) childrenUpdateObj.get(GIFTS_PREFERENCES);
                    for (Object newGift: giftsList) {
                        newGiftsPreferences.add(newGift.toString());
                    }
                    // Primesc noul niceScore din input
                    double newNiceScore = -1;
                    if (childrenUpdateObj.get(NICE_SCORE) != null) {
                        newNiceScore = Double.parseDouble(
                               childrenUpdateObj.get(NICE_SCORE).toString());
                    }
                    // Creez un obiect de tip ChildrenUpdates si ii adaug update-urile primite
                    ChildrenUpdates childrenUpdateToAdd = new ChildrenUpdates(
                            Integer.valueOf(childrenUpdateObj.get(ID).toString()),
                            newNiceScore, newGiftsPreferences);
                    // Adaug update-urile in lista
                    childrenUpdates.add(childrenUpdateToAdd);
                }
                // Creez un obiect de tip AnnualChanges si ii adaug schimbarile anuale
                AnnualChanges annualChangesToAdd = new AnnualChanges(
                        Double.valueOf(annualChangesObj.get(NEW_SANTA_BUDGET).toString()),
                        newGifts, newChildren, childrenUpdates);
                // Adaug schimbarile anuale in lista
                solver.getAnnualChanges().add(annualChangesToAdd);
            }

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}
