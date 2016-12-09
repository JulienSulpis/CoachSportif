package com.model;

import java.sql.*;
import java.util.ArrayList;

/**
 * Project : Coach Sportif.
 * Created by Julien on 04/12/2016.
 */
public class DataBase {
    private Connection conn;
    private Statement state;
    private ResultSet result;

    public void init() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            //System.out.println("Driver O.K.");

            String url = "jdbc:mysql://localhost/food_db";
            String user = "root";
            String passwd = "motdepasse";

            conn = DriverManager.getConnection(url, user, passwd);
            //System.out.println("Connexion réussie !");

            state = conn.createStatement();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<FoodStuff> request(String attribute, Object value){
        ArrayList<FoodStuff> request = new ArrayList<>();
        try{
            result = state.executeQuery("SELECT * FROM foodstuff WHERE " + attribute + " = \"" + value + "\"");

            int i = 0;
            while (result.next()){
                request.add(new FoodStuff());
                request.get(i).setName(result.getString(2));
                request.get(i).setPortion(result.getInt(3));
                request.get(i).setEnergyValue(result.getInt(4));
                request.get(i).setProteinValue(result.getFloat(5));
                request.get(i).setCarbohydratesValue(result.getFloat(6));
                request.get(i).setLipidValue(result.getFloat(7));
                request.get(i).setWaterValue(result.getFloat(8));
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return request;
    }

    public void close(){
        try {
            result.close();
            state.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
