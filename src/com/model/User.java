package com.model;

import java.util.ArrayList;

/**
 * Project : Coach Sportif.
 * Created by Julien on 04/12/2016.
 */
public class User {

    private String firstName;
    private String name;
    private int age;
    private int motivation;
    private float weight = 0;
    private int height = 1;
    private char sex;
    private float IMC;
    private int sportLevel;
    private ArrayList<String> foodRestriction= new ArrayList<String>();
    //private ArrayList<String> allergies; // compris dans foodRestriction ?
    private Goal goal = new Goal();
    private Preferences preferences = new Preferences();

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getMotivation() {
        return motivation;
    }

    public void setMotivation(int motivation) {
        this.motivation = motivation;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
        IMC = (float)((int)(100*this.weight / Math.pow((float)this.height/100, 2)))/100; // on ne conserve que 2 chiffres après la virgule
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
        IMC = (float)((int)(100*this.weight / Math.pow((float)this.height/100, 2)))/100; // on ne conserve que 2 chiffres après la virgule
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public float getIMC() {
        return IMC;
    }

    public void setIMC(float IMC) {
        this.IMC = IMC;
    }

    public int getSportLevel() {
        return sportLevel;
    }

    public void setSportLevel(int sportLevel) {
        this.sportLevel = sportLevel;
    }

    public ArrayList<String> getFoodRestriction() {
        return foodRestriction;
    }

    public void addFoodRestriction(String foodRestriction) {
        this.foodRestriction.add(foodRestriction);
    }

//    public ArrayList<String> getAllergies() {
//        return allergies;
//    }
//
//    public void setAllergies(ArrayList<String> allergies) {
//        this.allergies = allergies;
//    }

    public Goal getGoal() {
        return goal;
    }

    public void setGoal(Goal goal) {
        this.goal = goal;
    }

    public Preferences getPreferences() {
        return preferences;
    }

    public void setPreferences(Preferences preferences) {
        this.preferences = preferences;
    }

    public String toString(){
        String str = "";
        str += "First name : " + firstName + "\n";
        str += "Name : " + name + "\n";
        str += "Age : " + age + "\n";
        str += "Motivation : " + motivation + "\n";
        str += "Weight : " + weight + "\n";
        str += "Height : " + height + "\n";
        str += "Sex : " + sex + "\n";
        str += "IMC : " + IMC + "\n";
        str += "Sport level : " + sportLevel + "\n";
        str += "Food restrictions : " + foodRestriction + "\n";
        //str += "Allergies : " + allergies + "\n\n";
        str += goal.toString() + "\n\n";
        str += preferences.toString();
        return str;
    }
}
