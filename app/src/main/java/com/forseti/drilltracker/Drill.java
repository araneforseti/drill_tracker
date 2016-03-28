package com.forseti.drilltracker;

public class Drill {
    private String name;
    private String description;

    public Drill(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Drill() {

    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
