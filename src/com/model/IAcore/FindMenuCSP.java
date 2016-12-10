package com.model.IAcore;


import com.model.DataBase;
import com.model.FoodStuff;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Resolution of the 4-queens problem as a Constraint Satisfaction Problem.
 * The variables are the columns and their values (i.e. the domain) are the position of the queens in each column.
 * We use the constraint "no attack" defined in its own class. *
 */

public class FindMenuCSP extends CSP {

    public static final Variable vegetables = new Variable("vegetables");
    public static final Variable meat = new Variable("meat");
    public static final Variable dairy = new Variable("dairy");
    public static final Variable fruit = new Variable("fruit");
    public static float requestedEnergeticValue;


    public FindMenuCSP(float requestedEnergeticValue) {
        FindMenuCSP.requestedEnergeticValue = requestedEnergeticValue;
        collectVariables();

        DataBase db = new DataBase();
        db.init();

        ArrayList<FoodStuff> domain;

        domain = db.request("Famille", "Viandes");
        Collections.shuffle(domain);
        setDomain(meat, new Domain(domain));

        domain = db.request("Famille", "Légumes");
        Collections.shuffle(domain);
        setDomain(vegetables, new Domain(domain));

        domain = db.request("Famille", "Yaourts et spécialités laitières type yaourts");
        Collections.shuffle(domain);
        setDomain(dairy, new Domain(domain));

        domain = db.request("Famille", "Fruits frais");
        Collections.shuffle(domain);
        setDomain(fruit, new Domain(domain));

        db.close();

        addConstraint(new NutritionalValueConstraint(vegetables, meat, dairy, fruit, requestedEnergeticValue));
    }

    private void collectVariables() {
        addVariable(vegetables);
        addVariable(meat);
        addVariable(dairy);
        addVariable(fruit);
    }
}