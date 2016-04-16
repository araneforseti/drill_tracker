package com.forseti.drilltracker.regression;

import android.support.test.rule.ActivityTestRule;
import android.util.Log;

import com.forseti.drilltracker.DrillTrackerMain;
import com.forseti.drilltracker.data.Category;
import com.forseti.drilltracker.regression.steps.CategorySteps;

import org.junit.After;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class CategoryLifeCycleTest {
    @Rule
    public ActivityTestRule<DrillTrackerMain> mainActivityTestRule = new ActivityTestRule(DrillTrackerMain.class);

    @Test
    public void categoryLifecycle() {
        String categoryName = "New Category Name";
        String changedCategoryName = "Different Name Entirely";

        CategorySteps.addCategory(categoryName);
        onView(withText(categoryName)).check(matches(isDisplayed()));

        CategorySteps.editCategory(categoryName, changedCategoryName);
        onView(withText(categoryName)).check(doesNotExist());
        onView(withText(changedCategoryName)).check(matches(isDisplayed()));

        CategorySteps.deleteCategory(changedCategoryName);
        onView(withText(changedCategoryName)).check(doesNotExist());
    }

    @After
    public void tearDown() {
        mainActivityTestRule.getActivity().deleteAllData();
        Log.i("Data Cleanup", "Removing data");
    }
}