package com.forseti.drilltracker.regression;

import android.support.test.rule.ActivityTestRule;
import android.util.Log;

import com.forseti.drilltracker.DrillTrackerMain;
import com.forseti.drilltracker.R;
import com.forseti.drilltracker.data.Drill;
import com.forseti.drilltracker.regression.page.MainPage;
import com.forseti.drilltracker.regression.steps.CategorySteps;
import com.forseti.drilltracker.regression.steps.DrillSteps;
import com.forseti.drilltracker.regression.utils.Helpers;

import org.junit.After;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class DrillLifeCycleTest {
    @Rule
    public ActivityTestRule<DrillTrackerMain> mainActivityTestRule = new ActivityTestRule(DrillTrackerMain.class);

    @Test
    public void shouldNotShowCreateDrillWithNoCategories() {
        MainPage mainPage = new MainPage();
        mainPage.openCreateDrillDialog();
        Helpers.checkToastMessage(mainActivityTestRule.getActivity().getWindow().getDecorView(), R.string.no_categories);
    }

    @Test
    public void drillLifeCycle() {
        String categoryName = "Sample Category";
        CategorySteps.addCategory(categoryName);
        Drill newDrill = new Drill("Drill Name", "Drill description");
        newDrill.setInstructions("Drill Instructions");
        newDrill.setVideoURL("Drill URL");

        DrillSteps.addDrill(categoryName, newDrill);
        CategorySteps.toggleCategory(categoryName);
        onView(withText(newDrill.getName())).check(matches(isDisplayed()));

        String newDrillName = "New Drill Name";
        DrillSteps.editDrill(newDrill.getName(), newDrillName);
        onView(withText(newDrillName)).check(matches(isDisplayed()));


        CategorySteps.deleteCategory(categoryName);
        Helpers.checkToastMessage(mainActivityTestRule.getActivity().getWindow().getDecorView(), R.string.not_empty_category);

        DrillSteps.deleteDrill(newDrillName);
        onView(withText(newDrill.getName())).check(doesNotExist());

        CategorySteps.deleteCategory(categoryName);
        onView(withText(categoryName)).check(doesNotExist());
    }

    @After
    public void tearDown() {
        mainActivityTestRule.getActivity().deleteAllData();
        Log.i("Data Cleanup", "Removing data");
    }
}
