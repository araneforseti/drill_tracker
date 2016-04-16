package com.forseti.drilltracker.regression.steps;

import com.forseti.drilltracker.data.Drill;
import com.forseti.drilltracker.regression.page.CreateDrillPage;
import com.forseti.drilltracker.regression.page.EditDrillPage;
import com.forseti.drilltracker.regression.page.MainPage;

public class DrillSteps {
    public static void addDrill(String categoryName, Drill newDrill) {
        MainPage mainPage = new MainPage();
        mainPage.openCreateDrillDialog();

        CreateDrillPage createDrillPage = new CreateDrillPage();
        createDrillPage.createDill(categoryName, newDrill);
    }

    public static void deleteDrill(String drillName) {
        MainPage mainPage = new MainPage();
        mainPage.deleteDrill(drillName);
    }

    public static void editDrill(String currentDrillName, String newDrillName) {
        MainPage mainPage = new MainPage();
        mainPage.openEditDrillDialog(currentDrillName);

        EditDrillPage editDrillPage = new EditDrillPage();
        editDrillPage.setName(newDrillName);
        editDrillPage.save();
    }
}
