package entities;

import java.util.ArrayList;

public class AnnualChanges {
    private Double newSantaBudget;
    private ArrayList<Gift> newGifts;
    private ArrayList<Child> newChildren;
    private ArrayList<ChildrenUpdates> childrenUpdates;

    public AnnualChanges(Double newSantaBudget, ArrayList<Gift> newGifts, ArrayList<Child> newChildren, ArrayList<ChildrenUpdates> childrenUpdates) {
        this.newSantaBudget = newSantaBudget;
        this.newGifts = newGifts;
        this.newChildren = newChildren;
        this.childrenUpdates = childrenUpdates;
    }

    public Double getNewSantaBudget() {
        return newSantaBudget;
    }

    public void setNewSantaBudget(Double newSantaBudget) {
        this.newSantaBudget = newSantaBudget;
    }

    public ArrayList<Gift> getNewGifts() {
        return newGifts;
    }

    public void setNewGifts(ArrayList<Gift> newGifts) {
        this.newGifts = newGifts;
    }

    public ArrayList<Child> getNewChildren() {
        return newChildren;
    }

    public void setNewChildren(ArrayList<Child> newChildren) {
        this.newChildren = newChildren;
    }

    public ArrayList<ChildrenUpdates> getChildrenUpdates() {
        return childrenUpdates;
    }

    public void setChildrenUpdates(ArrayList<ChildrenUpdates> childrenUpdates) {
        this.childrenUpdates = childrenUpdates;
    }

    @Override
    public String toString() {
        return "AnnualChanges{" +
                "newSantaBudget=" + newSantaBudget +
                ", newGifts=" + newGifts +
                ", newChildren=" + newChildren +
                ", childrenUpdates=" + childrenUpdates +
                '}';
    }
}
