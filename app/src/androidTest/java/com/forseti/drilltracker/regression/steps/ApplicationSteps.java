package com.forseti.drilltracker.regression.steps;

import com.forseti.drilltracker.regression.page.MainPage;

public class ApplicationSteps {

    public static void importFile(String filename) {
        MainPage mainPage = new MainPage();
        mainPage.openMenu();
        mainPage.openImportMenu();
        mainPage.importFile(filename);
    }

    public static void openCreateDrillDialog() {
        MainPage mainPage = new MainPage();
        mainPage.openMenu();
        mainPage.openCreateDrillDialog();
        mainPage.openCreateDrillDialog();
    }
}
