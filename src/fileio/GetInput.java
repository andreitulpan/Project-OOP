package fileio;

import common.Utils;
import entities.AnnualChanges;
import entities.Child;
import entities.ChildrenUpdates;
import entities.Gift;
import solver.Solver;
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
import static common.Constants.ELF;
import static common.Constants.FIRST_NAME;
import static common.Constants.GIFTS_PREFERENCES;
import static common.Constants.ID;
import static common.Constants.INITIAL_DATA;
import static common.Constants.LAST_NAME;
import static common.Constants.NEW_CHILDREN;
import static common.Constants.NEW_GIFTS;
import static common.Constants.NEW_SANTA_BUDGET;
import static common.Constants.NICE_SCORE;
import static common.Constants.NICE_SCORE_BONUS;
import static common.Constants.NUMBER_OF_YEARS;
import static common.Constants.PRICE;
import static common.Constants.PRODUCT_NAME;
import static common.Constants.QUANTITY;
import static common.Constants.SANTA_BUDGET;
import static common.Constants.SANTA_GIFTS_LIST;
import static common.Constants.STRATEGY;

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
            solver.setNumberOfYears(Utils.getInteger(jsonObject, NUMBER_OF_YEARS));

            // SANTA BUDGET
            solver.setSantaBudget(Utils.getDouble(jsonObject, SANTA_BUDGET));

            JSONObject initialDataObj = (JSONObject) jsonObject.get(INITIAL_DATA);

            // CHILD
            getChild(solver, initialDataObj);

            // GIFTS
            getGifts(solver, initialDataObj);

            // ANNUAL CHANGES
            getAnnualChanges(solver, jsonObject);

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    private static void getChild(final Solver solver, final JSONObject initialDataObj) {
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
            Child child = new Child.ChildBuilder(
                    Utils.getInteger(childrenObj, ID),
                    Utils.getString(childrenObj, LAST_NAME),
                    Utils.getString(childrenObj, FIRST_NAME),
                    Utils.getString(childrenObj, CITY),
                    Utils.getInteger(childrenObj, AGE),
                    giftsPreferences)
                    .niceScoreBonus(Utils.getDouble(childrenObj, NICE_SCORE_BONUS))
                    .elf(Utils.getString(childrenObj, ELF))
                    .build();

            // Adaug niceScore-ul in lista copilului
            child.getNiceScoreHistory().add(Utils.getDouble(childrenObj, NICE_SCORE));

            // Adaug copilul de tip Child in lista
            solver.getChildren().add(child);

            Utils.updateCities(solver, child, Utils.getString(childrenObj, CITY));
        }
    }

    private static void getGifts(final Solver solver, final JSONObject initialDataObj) {
        JSONArray santaGiftsList = (JSONArray) initialDataObj.get(SANTA_GIFTS_LIST);
        // Parcurg lista cu caodurile din input
        for (Object object: santaGiftsList) {
            JSONObject santaGiftsListObj = (JSONObject) object;
            // Creez un cadou nou de tip Gift cu toate datele primite la input
            Gift gift = new Gift.GiftBuilder(
                    Utils.getString(santaGiftsListObj, PRODUCT_NAME),
                    Utils.getDouble(santaGiftsListObj, PRICE),
                    Utils.getString(santaGiftsListObj, CATEGORY))
                    .quantity(Utils.getInteger(santaGiftsListObj, QUANTITY))
                    .build();
            // Adaug cadoul de tip Gift in lista
            solver.getGifts().add(gift);
        }
    }

    private static void getAnnualChanges(final Solver solver, final JSONObject jsonObject) {
        JSONArray annualChangesList = (JSONArray) jsonObject.get(ANNUAL_CHANGES);
        // Parcurg lista cu schimbarile anuale din input
        for (Object object: annualChangesList) {
            JSONObject annualChangesObj = (JSONObject) object;

            // NEWGIFTS
            ArrayList<Gift> newGifts = getNewGifts(annualChangesObj);

            // NEWCHILDREN
            ArrayList<Child> newChild = getNewChild(annualChangesObj);

            // CHILDREN UPDATES
            ArrayList<ChildrenUpdates> childUpdates = getChildrenUpdates(annualChangesObj);

            // Creez un obiect de tip AnnualChanges si ii adaug schimbarile anuale
            AnnualChanges annualChangesToAdd = new AnnualChanges(
                    Utils.getDouble(annualChangesObj, NEW_SANTA_BUDGET),
                    newGifts, newChild,
                    Utils.getString(annualChangesObj, STRATEGY),
                    childUpdates);

            // Adaug schimbarile anuale in lista
            solver.getAnnualChanges().add(annualChangesToAdd);
        }
    }

    private static ArrayList<Gift> getNewGifts(final JSONObject annualChangesObj) {
        ArrayList<Gift> newGifts = new ArrayList<>();
        JSONArray newGiftsList = (JSONArray) annualChangesObj.get(NEW_GIFTS);
        // Parcurg lista cu noile cadouri din input
        for (Object newGiftObj: newGiftsList) {
            JSONObject newSantaGift = (JSONObject) newGiftObj;
            // Creez un cadou nou de tip Gift cu toate datele primite la input
            Gift newGift = new Gift.GiftBuilder(
                    Utils.getString(newSantaGift, PRODUCT_NAME),
                    Utils.getDouble(newSantaGift, PRICE),
                    Utils.getString(newSantaGift, CATEGORY))
                    .quantity(Utils.getInteger(newSantaGift, QUANTITY))
                    .build();
            // Adaug cadoul de tip Gift in lista
            newGifts.add(newGift);
        }
        return newGifts;
    }

    private static ArrayList<Child> getNewChild(final JSONObject annualChangesObj) {
        ArrayList<Child> newChild = new ArrayList<>();
        JSONArray newChildList = (JSONArray) annualChangesObj.get(NEW_CHILDREN);

        // Parcurg lista cu noii copii din input
        for (Object newChildrenObject: newChildList) {
            JSONObject newChildrenObj = (JSONObject) newChildrenObject;

            // Fac o lista de preferinta si adaug preferintele primite la input
            ArrayList<String> newGiftsPreferences = new ArrayList<>();
            JSONArray giftsList = (JSONArray) newChildrenObj.get(GIFTS_PREFERENCES);
            for (Object newGift: giftsList) {
                newGiftsPreferences.add(newGift.toString());
            }

            // Creez un copil nou de tip Child cu toate datele primite la input
            Child newChildrenToAdd = new Child.ChildBuilder(
                    Utils.getInteger(newChildrenObj, ID),
                    Utils.getString(newChildrenObj, LAST_NAME),
                    Utils.getString(newChildrenObj, FIRST_NAME),
                    Utils.getString(newChildrenObj, CITY),
                    Utils.getInteger(newChildrenObj, AGE),
                    newGiftsPreferences)
                    .niceScoreBonus(Utils.getDouble(newChildrenObj, NICE_SCORE_BONUS))
                    .elf(Utils.getString(newChildrenObj, ELF))
                    .build();

            // Adaug niceScore-ul in lista copilului
            newChildrenToAdd.getNiceScoreHistory().add(
                    Utils.getDouble(newChildrenObj, NICE_SCORE));

            // Adaug copilul de tip Child in lista
            newChild.add(newChildrenToAdd);
        }
        return newChild;
    }

    private static ArrayList<ChildrenUpdates> getChildrenUpdates(
                                                final JSONObject annualChangesObj) {
        ArrayList<ChildrenUpdates> childUpdates = new ArrayList<>();
        JSONArray childUpdatesList = (JSONArray) annualChangesObj.get(CHILDREN_UPDATES);

        // Parcurg lista cu update-urile pentru copii
        for (Object childrenUpdate: childUpdatesList) {
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
                newNiceScore = Utils.getDouble(childrenUpdateObj, NICE_SCORE);
            }

            // Creez un obiect de tip ChildrenUpdates si ii adaug update-urile primite
            ChildrenUpdates childrenUpdateToAdd = new ChildrenUpdates(
                    Utils.getInteger(childrenUpdateObj, ID),
                    newNiceScore, newGiftsPreferences,
                    Utils.getString(childrenUpdateObj, ELF));

            // Adaug update-urile in lista
            childUpdates.add(childrenUpdateToAdd);
        }
        return childUpdates;
    }
}
