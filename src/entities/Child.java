package entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import common.Constants;

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
    @JsonIgnore
    private Double niceScoreBonus;
    @JsonIgnore
    private String elf;

    public Child(final ChildBuilder builder) {
        this.id = builder.id;
        this.lastName = builder.lastName;
        this.firstName = builder.firstName;
        this.city = builder.city;
        this.age = builder.age;
        this.giftsPreferences = builder.giftsPreferences;
        this.averageScore = builder.averageScore;
        this.niceScoreHistory = builder.niceScoreHistory;
        this.assignedBudget = builder.assignedBudget;
        this.receivedGifts = builder.receivedGifts;
        this.niceScoreBonus = builder.niceScoreBonus;
        this.elf = builder.elf;
    }

    @SuppressWarnings("CopyConstructorMissesField")
    public Child(final Child child) {
        this.id = child.getId();
        this.lastName = child.getLastName();
        this.firstName = child.getFirstName();
        this.city = child.getCity();
        this.age = child.getAge();
        this.giftsPreferences = new ArrayList<>(child.getGiftsPreferences());
        this.averageScore =  child.getAverageScore();
        this.niceScoreHistory = new ArrayList<>(child.getNiceScoreHistory());
        this.assignedBudget = child.getAssignedBudget();
        this.receivedGifts = new ArrayList<>(child.getReceivedGifts());
        this.niceScoreBonus = child.getNiceScoreBonus();
        this.elf = child.getElf();
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

    public Double getNiceScoreBonus() {
        return niceScoreBonus;
    }

    public void setNiceScoreBonus(final Double niceScoreBonus) {
        this.niceScoreBonus = niceScoreBonus;
    }

    public String getElf() {
        return elf;
    }

    public void setElf(final String elf) {
        this.elf = elf;
    }

    public static final class ChildBuilder {
        private final Integer id;
        private final String lastName;
        private final String firstName;
        private final String city;
        private final Integer age;
        private final ArrayList<String> giftsPreferences;
        private final Double averageScore;
        private final ArrayList<Double> niceScoreHistory;
        private final Double assignedBudget;
        private final ArrayList<Gift> receivedGifts;
        private Double niceScoreBonus;
        private String elf;

        public ChildBuilder(final Integer id, final String lastName, final String firstName,
                            final String city, final Integer age,
                            final ArrayList<String> giftsPreferences) {
            this.id = id;
            this.lastName = lastName;
            this.firstName = firstName;
            this.city = city;
            this.age = age;
            this.giftsPreferences = giftsPreferences;
            this.niceScoreHistory = new ArrayList<>();
            this.receivedGifts = new ArrayList<>();
            this.averageScore = (double) 0;
            this.assignedBudget = (double) 0;
            this.niceScoreBonus = (double) 0;
            this.elf = Constants.NONE;
        }

        /**
         * Seteaza niceScoreBonus-ul prin
         * intermediul pattern-ului builder
         *
         * @param receivedNiceScoreBonus niceScoreBonus-ul ce va fi setat
         * @return instanta obiectului curent
         */
        public ChildBuilder niceScoreBonus(final Double receivedNiceScoreBonus) {
            this.niceScoreBonus = receivedNiceScoreBonus;
            return this;
        }

        /**
         * Seteaza elf-ul prin intermdiul
         * pattern-ului builder
         *
         * @param receivedElf elf-ul ce va fi setat
         * @return instanta obiectului curent
         */
        public ChildBuilder elf(final String receivedElf) {
            this.elf = receivedElf;
            return this;
        }

        /**
         * Intoarce o instanta nou
         * creata a obiectului Child
         *
         * @return instanta creata
         */
        public Child build() {
            return new Child(this);
        }
    }
}
