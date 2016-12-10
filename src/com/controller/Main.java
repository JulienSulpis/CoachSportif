package com.controller;

import com.model.DataBase;
import com.model.Menu;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Main {

    public static void main(String[] args) {

        DataBase db = new DataBase();
        db.init();

        GregorianCalendar cal = new GregorianCalendar();
        cal.set(2016, Calendar.DECEMBER, 10);

        Menu menu = new Menu(700);
        menu.setName("déjeuner");
        menu.setCalendar(cal);

        System.out.println(menu);

//        User user = new User();
//        UI ui = new UI();
//        ui.askForUserData(user);
//        System.out.println(user);
    }
}
