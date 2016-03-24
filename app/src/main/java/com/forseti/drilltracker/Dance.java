package com.forseti.drilltracker;

import java.util.ArrayList;

public class Dance {
    private String name;
    private String checkedType;
    private boolean checked;

    private ArrayList<Drill> drills;

    public Dance(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCheckedType() {
        return checkedType;
    }

    public void setCheckedType(String checkedType) {
        this.checkedType = checkedType;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public ArrayList<Drill> getDrills() {
        return drills;
    }

    public void setDrills(ArrayList<Drill> drills) {
        this.drills = drills;
    }
}
