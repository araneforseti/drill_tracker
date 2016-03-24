package com.forseti.drilltracker.temporary;

import com.forseti.drilltracker.Dance;
import com.forseti.drilltracker.Drill;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TemporaryDataUtils {
    public static ArrayList<Dance> createDummyData() {
        ArrayList<Dance> dances = new ArrayList<>();

        Dance west = new Dance("West Coast Swing");
        west.setDrills(createWestCoastSwingDrills());
        dances.add(west);

        Dance twoStep = new Dance("Two Step");
        twoStep.setDrills(createTwoStepDrills());
        dances.add(twoStep);

        Dance waltz = new Dance("Waltz");
        waltz.setDrills(createWaltzDrill());
        dances.add(waltz);

        return dances;
    }

    public static List<Dance> createDanceList() {
        List<Dance> dances = new ArrayList<>();
        Dance wcs = new Dance("West Coast Swing");
        wcs.setDrills(createWestCoastSwingDrills());
        dances.add(wcs);
        Dance twoStep = new Dance("Two Step");
        twoStep.setDrills(createTwoStepDrills());
        dances.add(twoStep);
        Dance waltz = new Dance("Waltz");
        waltz.setDrills(createWaltzDrill());
        dances.add(waltz);
        return dances;
    }

    public static HashMap<Dance, List<Drill>> createDrillList() {
        HashMap<Dance, List<Drill>> drillList = new HashMap<>();
        drillList.put(new Dance("West Coast Swing"), createWestCoastSwingDrills());
        drillList.put(new Dance("Two Step"), createTwoStepDrills());
        drillList.put(new Dance("Waltz"), createWaltzDrill());
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
