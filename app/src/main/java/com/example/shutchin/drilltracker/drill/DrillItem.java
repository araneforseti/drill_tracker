package com.example.shutchin.drilltracker.drill;

public class DrillItem {
    public final String id;
    public final String name;
    public final String description;

    public DrillItem(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;

    }

    @Override
    public String toString() {
        return description;
    }
}
