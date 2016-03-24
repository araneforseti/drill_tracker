package com.forseti.drilltracker.temporary;

import com.forseti.drilltracker.Category;
import com.forseti.drilltracker.Drill;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TemporaryDataUtils {
    public static ArrayList<Category> createDummyData() {
        ArrayList<Category> categories = new ArrayList<>();

        Category west = new Category("West Coast Swing");
        west.setDrills(createWestCoastSwingDrills());
        categories.add(west);

        Category twoStep = new Category("Two Step");
        twoStep.setDrills(createTwoStepDrills());
        categories.add(twoStep);

        Category waltz = new Category("Waltz");
        waltz.setDrills(createWaltzDrill());
        categories.add(waltz);

        return categories;
    }

    public static List<Category> createDanceList() {
        List<Category> categories = new ArrayList<>();
        Category wcs = new Category("West Coast Swing");
        wcs.setDrills(createWestCoastSwingDrills());
        categories.add(wcs);
        Category twoStep = new Category("Two Step");
        twoStep.setDrills(createTwoStepDrills());
        categories.add(twoStep);
        Category waltz = new Category("Waltz");
        waltz.setDrills(createWaltzDrill());
        categories.add(waltz);
        return categories;
    }

    public static HashMap<Category, List<Drill>> createDrillList() {
        HashMap<Category, List<Drill>> drillList = new HashMap<>();
        drillList.put(new Category("West Coast Swing"), createWestCoastSwingDrills());
        drillList.put(new Category("Two Step"), createTwoStepDrills());
        drillList.put(new Category("Waltz"), createWaltzDrill());
        return drillList;
    }

    public static ArrayList<Drill> createWestCoastSwingDrills() {
        ArrayList<Drill> drills = new ArrayList<>();
        drills.add(new Drill("Ankle", "Balance on one foot, moving the heel up and down in a controlled manner"));
        drills.add(new Drill("Pulse", "Subtle pulse to the music"));
        return drills;
    }

    public static ArrayList<Drill> createTwoStepDrills() {
        ArrayList<Drill> drills = new ArrayList<>();
        drills.add(new Drill("Shinea", "Do them, focusing on spotting and getting completely over foot"));
        drills.add(new Drill("Arm Finishing", "Spin on one foot and let body naturally open, finishing arm"));
        return drills;
    }

    public static ArrayList<Drill> createWaltzDrill() {
        ArrayList<Drill> drills = new ArrayList<>();
        drills.add(new Drill("Contrabody", "Practice contrabody"));
        drills.add(new Drill("Half Squat", "Take step as far as possible"));
        return drills;
    }
}
