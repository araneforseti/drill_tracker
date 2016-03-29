package com.forseti.drilltracker.data;

public class Drill {
    private String name;
    private String description;
    private String instructions;
    private String videoURL;

    public Drill(String name, String description) {
        this.name = name;
        this.description = description;
        this.instructions = "";
    }

    public Drill() {
        this.name = "";
        this.description = "";
        this.instructions = "";
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public void setVideoURL(String videoURL) {
        this.videoURL = videoURL;
    }

    public String getVideoURL() {
        return videoURL;
    }
}
