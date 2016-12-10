package com.model.IAcore;

import com.model.FoodStuff;

import java.util.ArrayList;
import java.util.List;


public class NutritionalValueConstraint implements Constraint {

    private Variable vegetables;
    private Variable meat;
    private Variable dairy;
    private Variable fruit;
    private float requestedEnergeticValue;
    private List<Variable> scope;

    public NutritionalValueConstraint(Variable vegetables, Variable meat, Variable dairy, Variable fruit, float requestedEnergeticValue) {
        this.vegetables = vegetables;
        this.meat = meat;
        this.dairy = dairy;
        this.fruit = fruit;
        this.requestedEnergeticValue = requestedEnergeticValue;
        scope = new ArrayList<Variable>(4);
        scope.add(this.vegetables);
        scope.add(this.meat);
        scope.add(this.dairy);
        scope.add(this.fruit);
    }

    @Override
    public List<Variable> getScope() {
        return scope;
    }

    @Override
    public boolean isSatisfiedWith(Assignment assignment) {
        FoodStuff vegetables = (FoodStuff) assignment.getAssignment(this.vegetables);
        FoodStuff meat = (FoodStuff) assignment.getAssignment(this.meat);
        FoodStuff dairy = (FoodStuff) assignment.getAssignment(this.dairy);
        FoodStuff fruit = (FoodStuff) assignment.getAssignment(this.fruit);

        float totalEnergy;
        float totalProtein;
        float totalLipid;
        float totalCarbohydratesValue;
        float totalWeight;

        try {
            totalEnergy = vegetables.getEnergyValue() + meat.getEnergyValue() + dairy.getEnergyValue() + fruit.getEnergyValue();
            totalProtein = vegetables.getProteinValue() + meat.getProteinValue() + dairy.getProteinValue() + fruit.getProteinValue();
            totalLipid = vegetables.getLipidValue() + meat.getLipidValue() + dairy.getLipidValue() + fruit.getLipidValue();
            totalCarbohydratesValue = vegetables.getCarbohydratesValue() + meat.getCarbohydratesValue() + dairy.getCarbohydratesValue() + fruit.getCarbohydratesValue();
            totalWeight = totalProtein + totalLipid + totalCarbohydratesValue;
        } catch (NullPointerException e) {
            return true; // on n'a pas tous les éléments du menu, donc on renvoie true pour continuer à chercher
        }


        return totalEnergy > requestedEnergeticValue * 0.90 && totalEnergy < requestedEnergeticValue * 1.1
                && totalProtein > totalWeight * 0.15 && totalProtein < totalWeight * 0.3
                && totalLipid > totalWeight * 0.28 && totalLipid < totalWeight * 0.38
                && totalCarbohydratesValue > totalWeight * 0.4 && totalCarbohydratesValue < totalWeight * 0.55;

    }

}