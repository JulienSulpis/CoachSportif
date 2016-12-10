package com.model;

/**
 * Project : Coach Sportif.
 * Created by Julien on 04/12/2016.
 */
public class FoodStuff {

    private String name;
    private int portion;
    private int energyValue;
    private float proteinValue;
    private float lipidValue;
    private float carbohydratesValue;
    private float waterValue;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPortion() {
        return portion;
    }

    public void setPortion(int portion) {
        this.portion = portion;
    }

    public int getEnergyValue() {
        return energyValue;
    }

    public void setEnergyValue(int energyValue) {
        this.energyValue = energyValue;
    }

    public float getProteinValue() {
        return proteinValue;
    }

    public void setProteinValue(float proteinValue) {
        this.proteinValue = proteinValue;
    }

    public float getLipidValue() {
        return lipidValue;
    }

    public void setLipidValue(float lipidValue) {
        this.lipidValue = lipidValue;
    }

    public float getCarbohydratesValue() {
        return carbohydratesValue;
    }

    public void setCarbohydratesValue(float carbohydratesValue) {
        this.carbohydratesValue = carbohydratesValue;
    }

    public float getWaterValue() {
        return waterValue;
    }

    public void setWaterValue(float waterValue) {
        this.waterValue = waterValue;
    }

    public String toString(){
//        String str ="";
//        str += "Name : " +  name + "\n";
//        str += "Portion : " + portion + "g\n";
//        str += "Energy value : " + energyValue + "kcal\n";
//        str += "Protein value : " + proteinValue + "g\n";
//        str += "Lipid value : " + lipidValue + "g\n";
//        str += "Carbohydrates value : " + carbohydratesValue + "g\n"; // = glucides
//        str += "Water value : " + waterValue + "g\n";
        return name;
    }

}
