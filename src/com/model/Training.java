package com.model;

import java.util.Calendar;

/**
 * Project : Coach Sportif.
 * Created by Julien on 04/12/2016.
 */
public class Training {

    private Calendar calendar;
    private int duration;
    private int distance;
    private int consumedEnergy;


    public Calendar getCalendar() {
        return calendar;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getConsumedEnergy() {
        return consumedEnergy;
    }

    public void setConsumedEnergy(int consumedEnergy) {
        this.consumedEnergy = consumedEnergy;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
}
