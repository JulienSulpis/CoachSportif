package com.model;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Project : Coach Sportif.
 * Created by Julien on 04/12/2016.
 */
public class Menu {

    private String name="";
    private int energyValue;
    private ArrayList<FoodStuff> starter = new ArrayList<>();
    private ArrayList<FoodStuff> mainDish = new ArrayList<>();
    private ArrayList<FoodStuff> pudding = new ArrayList<>();
    private Calendar calendar;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEnergyValue() {
        return energyValue;
    }

    public void setEnergyValue(int energyValue) {
        this.energyValue = energyValue;
    }

    public ArrayList<FoodStuff> getStarter() {
        return starter;
    }

    public void addStarter(FoodStuff fs) {
        starter.add(fs);
        refreshEnergyValue(starter);
    }

    public ArrayList<FoodStuff> getMainDish() {
        return mainDish;
    }

    public void addMainDish(FoodStuff fs) {
        mainDish.add(fs);
        refreshEnergyValue(mainDish);
    }

    public ArrayList<FoodStuff> getPudding() {
        return pudding;
    }

    public void addPudding(FoodStuff fs) {
        pudding.add(fs);
        refreshEnergyValue(pudding);
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }

    private void refreshEnergyValue(ArrayList<FoodStuff> foodStuffs){
        for (FoodStuff fs : foodStuffs)
            energyValue += fs.getEnergyValue();
    }


    public String toString(){
        String str = "--- Menu du " + name + " du ";

        str += calendar.get(Calendar.DAY_OF_MONTH) + "/" + (calendar.get(Calendar.MONTH)+1) + "/" + calendar.get(Calendar.YEAR) + "---";
        //str += "starter : ";
        //for (FoodStuff fs : starter)
        //    str += fs.getName() + " (" + fs.getPortion() + "g); ";
        //str = str.substring(0, str.length()-2); // retire les 2 caractères "; " après le dernier aliment
        str += "\nplat principal : ";
        for (FoodStuff fs : mainDish)
            str += fs.getName() + " (" + fs.getPortion() + "g); ";
        str = str.substring(0, str.length()-2);
        str += "\ndessert : ";
        for (FoodStuff fs : pudding)
            str += fs.getName() + " (" + fs.getPortion() + "g); ";
        str = str.substring(0, str.length()-2);
        str += "\nvaleur énergétique : " + energyValue +" kcal";
        return str;
    }
}
