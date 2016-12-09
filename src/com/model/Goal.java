package com.model;

/**
 * Project : Coach Sportif.
 * Created by Julien on 04/12/2016.
 */
public class Goal {

    private float weightGoal;
    private boolean stayFit = false;

    public float getWeightGoal() {
        return weightGoal;
    }

    public void setWeightGoal(float weightGoal) {
        this.weightGoal = weightGoal;
    }

    public boolean isStayFit() {
        return stayFit;
    }

    public void setStayFit(boolean stayFit) {
        this.stayFit = stayFit;
    }

    public String toString(){
        return "weight goal : " + weightGoal + ", stay fit : " + (stayFit ? "yes" : "no");
    }
}
