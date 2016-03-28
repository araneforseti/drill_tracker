package com.forseti.drilltracker;

import java.util.ArrayList;

public class Category {
    private String name;

    private ArrayList<Drill> drills;

    public Category(String name) {
        this.name = name;
        this.drills = new ArrayList<>();
    }

    public Category() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Drill> getDrills() {
        return drills;
    }

    public void setDrills(ArrayList<Drill> drills) {
        this.drills = drills;
    }

    @Override
    public String toString() {
        return name + " Drills: " + drills.toString();
    }
}
