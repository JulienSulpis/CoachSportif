package com.view;

import com.model.User;

import java.util.Scanner;

/**
 * Project : Coach Sportif.
 * Created by Julien on 04/12/2016.
 */
public class UI {
    Scanner sc = new Scanner(System.in);

    public void askForUserData(User user){

        user.setFirstName(askForStringValue("What is your first name ?"));
        user.setName(askForStringValue("What is your name ?"));
        user.setAge(askForIntegerValue("How old are you ?"));
        user.setWeight(askForFloatValue("What is your weight in kilograms ?"));
        user.setHeight(askForIntegerValue("What is your height in centimeters ?"));
        user.setSex((askForStringValue("Are you a male or a female ? (M/F)")).charAt(0));
        user.addFoodRestriction(askForStringValue("Is there any foodstuff you cannot eat ?"));
        user.setSportLevel(askForIntegerValue("What is your sport level on a scale from 1 to 3 ?"));
        user.setMotivation(askForIntegerValue("What is your motivation on a scale from 1 to 7 ?"));

        if (askForStringValue("Do you just want to stay fit ? (not to loose weight)").equals("yes"))
            user.getGoal().setStayFit(true);
        else
            user.getGoal().setWeightGoal(askForFloatValue("If not, what is your weight goal ?"));

        if (askForStringValue("Do you take starters at lunch ?").equals("yes"))
            user.getPreferences().setDinnerStarter(true);
        if (askForStringValue("Do you take starters at dinner ?").equals("yes"))
            user.getPreferences().setLunchStarter(true);
        if (askForStringValue("Do you eat meat at dinner ?").equals("yes"))
            user.getPreferences().setMeatForDinner(true);
        if (askForStringValue("Do you take breakfasts ?").equals("yes"))
            user.getPreferences().setDinnerStarter(true);
    }

    private String askForStringValue(String message){
        System.out.println(message);
        return sc.nextLine();
    }

    private int askForIntegerValue(String message){
        System.out.println(message);
        int answer = sc.nextInt();
        sc.nextLine();
        return answer;
    }

    private float askForFloatValue(String message){
        System.out.println(message);
        float answer = sc.nextFloat();
        sc.nextLine();
        return answer;
    }
}