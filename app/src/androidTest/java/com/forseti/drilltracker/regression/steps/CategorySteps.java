package com.forseti.drilltracker.regression.steps;

import com.forseti.drilltracker.regression.page.CreateCategoryPage;
import com.forseti.drilltracker.regression.page.EditCategoryPage;
import com.forseti.drilltracker.regression.page.MainPage;

public class CategorySteps {
    public static void addCategory(String categoryName) {
        MainPage mainPage = new MainPage();
        mainPage.openAddCategoryDialog();

        CreateCategoryPage createCategoryPage = new CreateCategoryPage();
        createCategoryPage.createCategory(categoryName);
    }

    public static void deleteCategory(String categoryName) {
        MainPage mainPage = new MainPage();
        mainPage.deleteCategory(categoryName);
    }

    public static void toggleCategory(String categoryName) {
        MainPage mainPage = new MainPage();
        mainPage.clickCategory(categoryName);
    }

    public static void editCategory(String categoryName, String changedCategoryName) {
        MainPage mainPage = new MainPage();
        mainPage.openEditCategory(categoryName);

        EditCategoryPage editCategoryPage = new EditCategoryPage();
        editCategoryPage.setName(changedCategoryName);
        editCategoryPage.save();
    }
}
