package com.controller;

import com.model.DataBase;
import com.model.FoodStuff;
import com.model.Menu;
import com.model.User;
import com.view.UI;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Main {

    public static void main(String[] args) {

        DataBase db = new DataBase();
        db.init();

        GregorianCalendar cal = new GregorianCalendar();
        cal.set(2016, Calendar.DECEMBER, 10);

        Menu menu = new Menu();
        menu.setName("déjeuner");
        menu.setCalendar(cal);

        ArrayList<FoodStuff> result = db.request("Famille","Légumes");
        menu.addMainDish(result.get(0));
        result = db.request("Famille", "Viandes");
        menu.addMainDish(result.get(0));
        result = db.request("Famille", "Yaourts et spécialités laitières type yaourts");
        menu.addPudding(result.get(0));

        System.out.println(menu);

        System.out.println();
        System.out.println(menu.getMainDish().get(1));

        db.close();

//        FoodStuff carotte = new FoodStuff();
//        carotte.setCarbohydratesValue(2);
//        carotte.setEnergyValue(100);
//        carotte.setLipidValue(5);
//        carotte.setName("carotte");
//        carotte.setPortion(80);
//        carotte.setWaterValue(10);
//        carotte.setProteinValue(8);
//        System.out.println(carotte);

//        menu.addStarter(new ArrayList<FoodStuff>(){{add(carotte);}});
//        menu.addMainDish(new ArrayList<FoodStuff>(){{add(carotte);}});
//        menu.addPudding(new ArrayList<FoodStuff>(){{add(carotte);}});

        User user = new User();
        UI ui = new UI();
        ui.askForUserData(user);
        System.out.println(user);

    }
}
