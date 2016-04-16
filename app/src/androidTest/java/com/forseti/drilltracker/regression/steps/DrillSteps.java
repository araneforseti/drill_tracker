package com.forseti.drilltracker.regression.steps;

import com.forseti.drilltracker.data.Drill;
import com.forseti.drilltracker.regression.page.CreateDrillPage;
import com.forseti.drilltracker.regression.page.MainPage;

import static android.support.test.espresso.Espresso.onView;

public class DrillSteps {
    public static void addDrill(String categoryName, Drill newDrill) {
        MainPage mainPage = new MainPage();
        mainPage.openCreateDrillDialog();

        CreateDrillPage createDrillPage = new CreateDrillPage();
        createDrillPage.createDill(categoryName, newDrill);
    }

    public static void deleteDrill(String categoryName, Drill newDrill) {
        MainPage mainPage = new MainPage();
        mainPage.deleteDrill(categoryName, newDrill.getName());
    }
}
