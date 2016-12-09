package com.model;

/**
 * Project : Coach Sportif.
 * Created by Julien on 04/12/2016.
 */
public class Preferences {

    private boolean lunchStarter = false;
    private boolean dinnerStarter = false;
    private boolean meatForDinner = false;
    private boolean breakfast = false;

    public boolean isLunchStarter() {
        return lunchStarter;
    }

    public void setLunchStarter(boolean lunchStarter) {
        this.lunchStarter = lunchStarter;
    }

    public boolean isDinnerStarter() {
        return dinnerStarter;
    }

    public void setDinnerStarter(boolean dinnerStarter) {
        this.dinnerStarter = dinnerStarter;
    }

    public boolean isMeatForDinner() {
        return meatForDinner;
    }

    public void setMeatForDinner(boolean meatForDinner) {
        this.meatForDinner = meatForDinner;
    }

    public boolean isBreakfast() {
        return breakfast;
    }

    public void setBreakfast(boolean breakfast) {
        this.breakfast = breakfast;
    }

    public String toString(){
        String str = "";
        str += "Lunch starter : " + (lunchStarter ? "yes\n" : "no\n");
        str += "Dinner starter : " + (dinnerStarter ? "yes\n" : "no\n");
        str += "Meat for dinner : " + (meatForDinner ? "yes\n" : "no\n");
        str += "Breakfast : " + (breakfast ? "yes\n" : "no\n");
        return str;
    }
}
