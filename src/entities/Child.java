package entities;

import java.util.ArrayList;

public final class Child {
    private final Integer id;
    private String lastName;
    private String firstName;
    private String city;
    private Integer age;
    private ArrayList<String> giftsPreferences;
    private Double averageScore;
    private ArrayList<Double> niceScoreHistory;
    private Double assignedBudget;
    private ArrayList<Gift> receivedGifts;

    public Child(final Integer id, final String lastName, final String firstName,
                 final String city, final Integer age, final ArrayList<String> giftsPreferences) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.city = city;
        this.age = age;
        this.giftsPreferences = giftsPreferences;
        this.averageScore = (double) 0;
        this.niceScoreHistory = new ArrayList<>();
        this.assignedBudget = (double) 0;
        this.receivedGifts = new ArrayList<>();
    }

    public Child(final Integer id, final String lastName, final String firstName,
                 final String city, final Integer age, final ArrayList<String> giftsPreferences,
                 final Double averageScore, final ArrayList<Double> niceScoreHistory,
                 final Double assignedBudget, final ArrayList<Gift> receivedGifts) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.city = city;
        this.age = age;
        this.giftsPreferences = giftsPreferences;
        this.averageScore = averageScore;
        this.niceScoreHistory = niceScoreHistory;
        this.assignedBudget = assignedBudget;
        this.receivedGifts = receivedGifts;
    }

    @SuppressWarnings("CopyConstructorMissesField")
    public Child(final Child child) {
        this(child.getId(), child.getLastName(), child.getFirstName(), child.getCity(),
                child.getAge(), new ArrayList<>(child.getGiftsPreferences()),
                child.getAverageScore(), new ArrayList<>(child.getNiceScoreHistory()),
                child.getAssignedBudget(), new ArrayList<>(child.getReceivedGifts()));
    }

    public Integer getId() {
        return id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(final String city) {
        this.city = city;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(final Integer age) {
        this.age = age;
    }

    public ArrayList<String> getGiftsPreferences() {
        return giftsPreferences;
    }

    public void setGiftsPreferences(final ArrayList<String> giftsPreferences) {
        this.giftsPreferences = giftsPreferences;
    }

    public Double getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(final Double averageScore) {
        this.averageScore = averageScore;
    }

    public ArrayList<Double> getNiceScoreHistory() {
        return niceScoreHistory;
    }

    public void setNiceScoreHistory(final ArrayList<Double> niceScoreHistory) {
        this.niceScoreHistory = niceScoreHistory;
    }

    public Double getAssignedBudget() {
        return assignedBudget;
    }

    public void setAssignedBudget(final Double assignedBudget) {
        this.assignedBudget = assignedBudget;
    }

    public ArrayList<Gift> getReceivedGifts() {
        return receivedGifts;
    }

    public void setReceivedGifts(final ArrayList<Gift> receivedGifts) {
        this.receivedGifts = receivedGifts;
    }

}
