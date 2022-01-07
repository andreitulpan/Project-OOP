package fileio;

import entities.Child;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public final class ChildOutput {
    private ChildOutput() {
        ///constructor for checkstyle
    }

    /**
     * Adauga un copil in array-ul copiiilor pentru output.
     * Creeaza un nou copil cand il adauga, deoarece
     * se modifica datele copilului la update-uri.
     *
     * @param child Copilul ce trebuie adaugat
     * @param childrenArray Vectorul de tip JSON pentru copii
     */
    @SuppressWarnings("unchecked")
    public static void setChild(final Child child, final JSONArray childrenArray) {
        childrenArray.add(new Child(child));
    }

    /**
     * Adauga vectorul JSON de copii intr-un vector JSON de output
     *
     * @param childrenArray vectorul JSON de copii
     * @param outputArray vectorul JSON de output
     */
    @SuppressWarnings("unchecked")
    public static void setData(final JSONArray childrenArray, final JSONArray outputArray) {
        JSONObject childrenObj = new JSONObject();
        childrenObj.put("children", childrenArray);
        outputArray.add(childrenObj);
    }

}
