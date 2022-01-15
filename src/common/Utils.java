package common;

import entities.Child;
import entities.ChildrenUpdates;
import entities.City;
import org.json.simple.JSONObject;
import solver.Solver;

import java.util.ArrayList;
import java.util.Collections;

import static common.Constants.AGE_18;

public final class Utils {

    private Utils() {
        ///constructor for checkstyle
    }

    /**
     * Extrage un String dintru-un JSON OBject
     * @param object obiectul JSON
     * @param parameter tipul string-ului extras
     * @return string-ul extras
     */
    private static String getValue(final JSONObject object, final String parameter) {
        return object.get(parameter).toString();
    }

    /**
     * Extrage un Integer dintru-un JSON OBject
     * @param object obiectul JSON
     * @param parameter tipul integer-ului extras
     * @return integer-ul extras
     */
    public static Integer getInteger(final JSONObject object, final String parameter) {
        return Integer.valueOf(getValue(object, parameter));
    }

    /**
     * Extrage un Double dintru-un JSON OBject
     * @param object obiectul JSON
     * @param parameter tipul double-ului extras
     * @return double-ul extras
     */
    public static Double getDouble(final JSONObject object, final String parameter) {
        return Double.valueOf(getValue(object, parameter));
    }

    /**
     * Extrage un string dintru-un JSON OBject
     * @param object obiectul JSON
     * @param parameter tipul string-ului extras
     * @return string-ul extras
     */
    public static String getString(final JSONObject object, final String parameter) {
        return getValue(object, parameter);
    }

    /**
     * Adauga orasul copilului in lista daca
     * acesta nu exista sau in caz contrar
     * adauga copilul in lista orasului
     * @param solver solver-ul
     * @param children copilul
     * @param cityName numele orasului
     */
    public static void updateCities(final Solver solver,
                                    final Child children,
                                    final String cityName) {

        City city = solver.findCity(cityName);
        if (city != null) {
            city.getChild().add(children);
        } else {
            City newCity = new City(cityName);
            newCity.getChild().add(children);
            solver.getCities().add(newCity);
        }
    }

    /**
     * Adauga copiii noi in lista si
     * updateaza si lista oraselor
     * @param solver solver-ul
     * @param year anul in care se face update-ul
     */
    public static void updateChildList(final Solver solver, final Integer year) {
        for (Child newChild: solver.getAnnualChanges().get(year).getNewChildren()) {

            if (newChild.getAge() <= AGE_18) {
                solver.getChildren().add(newChild);
                Utils.updateCities(solver, newChild, newChild.getCity());
            }
        }
    }

    /**
     * Face update-urile necesare
     * anului asupra copiiilor
     * @param solver solver-ul
     * @param year anul in care se fac update-urile
     */
    public static void updateChild(final Solver solver, final Integer year) {
        for (Child child: solver.getChildren()) {

            // Se reinitializeaza lista de cadouri
            child.setReceivedGifts(new ArrayList<>());

            ArrayList<ChildrenUpdates> childrenUpdatesList =
                    solver.getAnnualChanges().get(year).getChildrenUpdates();

            // Parcurg lista de update-uri
            for (ChildrenUpdates childrenUpdates : childrenUpdatesList) {

                // Fac update copilului daca are acelasi ID
                if (childrenUpdates.getId().equals(child.getId())) {

                    // Adaug noul niceScore in lista daca acesta exista (!= -1)
                    if (childrenUpdates.getNewNiceScore() != -1) {
                        child.getNiceScoreHistory().add(childrenUpdates.getNewNiceScore());
                    }

                    // Salvez noile preferinte intr-o lista
                    ArrayList<String> newGiftsPreferences = new ArrayList<>();
                    for (String element : childrenUpdates.getNewGiftsPreferences()) {
                        if (!newGiftsPreferences.contains(element)) {
                            newGiftsPreferences.add(element);
                        }
                    }

                    Collections.reverse(newGiftsPreferences);

                    // Fac update listei de preferinte a copilului
                    for (String category : newGiftsPreferences) {
                        child.getGiftsPreferences().remove(category);
                        child.getGiftsPreferences().add(0, category);
                    }

                    if (childrenUpdates.getNewElf() != null) {
                        child.setElf(childrenUpdates.getNewElf());
                    }
                }
            }
        }
    }

    /**
     * Updateaza lista de cadouri a
     * mosului si bugetul acestuia
     * @param solver solver-ul
     * @param year anul in care se fac update-urile
     */
    public static void updateGiftsAndBudget(final Solver solver, final Integer year) {

        // Se adauga noile cadouri
        solver.getGifts().addAll(
                solver.getAnnualChanges().get(year).getNewGifts());

        // Se updateaza bugetul mosului
        solver.setSantaBudget(
                solver.getAnnualChanges().get(year).getNewSantaBudget());
    }

    /**
     * Sorteaza o lista de copii descendent
     * dupa averageScore si lexicografic
     * @param input lista de copii
     */
    public static void sortAverage(final ArrayList<Child> input) {
        input.sort((c1, c2) -> {
            int comparator = Double.compare(c2.getAverageScore(), c1.getAverageScore());
            if (comparator == 0) {
                return c1.getId() - c2.getId();
            }
            return comparator;
        });
    }

    /**
     * Sorteaza o lista de orase descendent
     * dupa averageNiceScore si lexicografic
     * @param input lista de orase
     */
    public static void sortCities(final ArrayList<City> input) {
        input.sort((c1, c2) -> {
            int comparator = Double.compare(c2.getNiceScore(), c1.getNiceScore());
            if (comparator == 0) {
                return c1.getName().compareTo(c2.getName());
            }
            return comparator;
        });
    }
}
