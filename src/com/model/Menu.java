package com.model;

import com.model.IAcore.Assignment;
import com.model.IAcore.BacktrackingStrategy;
import com.model.IAcore.FindMenuCSP;
import com.model.IAcore.Variable;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Project : Coach Sportif.
 * Created by Julien on 04/12/2016.
 */
public class Menu {

    private String name="";
    private float energeticValue;
    private float proteinValue;
    private float lipidValue;
    private float carbohydratesValue;
    private float waterValue;
    private ArrayList<FoodStuff> starter = new ArrayList<>();
    private ArrayList<FoodStuff> mainDish = new ArrayList<>();
    private ArrayList<FoodStuff> dairy = new ArrayList<>();
    private ArrayList<FoodStuff> pudding = new ArrayList<>();
    private Calendar calendar;

    public Menu(float energeticValue) {
        FindMenuCSP problem = new FindMenuCSP(energeticValue);
        BacktrackingStrategy bts = new BacktrackingStrategy();
//        bts.addCSPStateListener(new CSPStateListener() {
//            @Override
//            public void stateChanged(Assignment assignment, CSP csp) {
//                System.out.println("Assignment evolved : " + assignment);
//            }
//
//            @Override
//            public void stateChanged(CSP csp) {
//                System.out.println("CSP evolved : " + csp);
//            }
//
//        });

        Assignment sol = bts.solve(problem);
        addMainDish((FoodStuff) sol.getAssignment(new Variable("vegetables")));
        addMainDish((FoodStuff) sol.getAssignment(new Variable("meat")));
        addDairy((FoodStuff) sol.getAssignment(new Variable("dairy")));
        addPudding((FoodStuff) sol.getAssignment(new Variable("fruit")));
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getEnergeticValue() {
        return energeticValue;
    }

    public void setEnergeticValue(int energeticValue) {
        this.energeticValue = energeticValue;
    }

    public ArrayList<FoodStuff> getStarter() {
        return starter;
    }

    public void addStarter(FoodStuff fs) {
        starter.add(fs);
        refreshNutritionalValue(starter);
    }

    public ArrayList<FoodStuff> getMainDish() {
        return mainDish;
    }

    public void addMainDish(FoodStuff fs) {
        mainDish.add(fs);
        refreshNutritionalValue(mainDish);
    }

    public ArrayList<FoodStuff> getDairy() {
        return dairy;
    }

    public void addDairy(FoodStuff fs) {
        dairy.add(fs);
        refreshNutritionalValue(dairy);
    }

    public ArrayList<FoodStuff> getPudding() {
        return pudding;
    }

    public void addPudding(FoodStuff fs) {
        pudding.add(fs);
        refreshNutritionalValue(pudding);
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }

    private void refreshNutritionalValue(ArrayList<FoodStuff> foodStuffs) {
        for (FoodStuff fs : foodStuffs) {
            energeticValue += fs.getEnergyValue();
            proteinValue += fs.getProteinValue();
            lipidValue += fs.getLipidValue();
            carbohydratesValue += fs.getCarbohydratesValue();
            waterValue += fs.getWaterValue();
        }


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
        str += "\nproduit laitier : ";
        for (FoodStuff fs : dairy)
            str += fs.getName() + " (" + fs.getPortion() + "g); ";
        str = str.substring(0, str.length() - 2);
        str += "\ndessert : ";
        for (FoodStuff fs : pudding)
            str += fs.getName() + " (" + fs.getPortion() + "g); ";
        str = str.substring(0, str.length()-2);
        str += "\nvaleur énergétique : " + energeticValue + " kcal";
        str += "\nprotéines :  " + proteinValue + " g";
        str += "\nlipides : " + lipidValue + " g";
        str += "\nglucides : " + carbohydratesValue + " g";
        str += "\neau : " + waterValue + " g";
        return str;
    }
}