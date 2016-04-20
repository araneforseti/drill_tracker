package com.forseti.drilltracker.regression;

// Note: Tests assume there is a file "file.txt" located in Android's downloads

import android.support.test.rule.ActivityTestRule;

import com.forseti.drilltracker.DrillTrackerMain;
import com.forseti.drilltracker.regression.steps.ApplicationSteps;
import com.forseti.drilltracker.regression.steps.CategorySteps;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class ImportDataFlowTest {
    @Rule
    public ActivityTestRule<DrillTrackerMain> mainActivityTestRule = new ActivityTestRule(DrillTrackerMain.class);

    @Before
    public void setup() {
        mainActivityTestRule.getActivity().deleteAllData();
    }

    @Test
    public void shouldImportDrill() {
        ApplicationSteps.importFile("file.txt");

        onView(withText("Sample Drill")).check(matches(isDisplayed()));
        CategorySteps.toggleCategory("Sample Drill");
        onView(withText("First Drill")).check(matches(isDisplayed()));
    }

    @After
    public void teardown() {
        mainActivityTestRule.getActivity().deleteAllData();
    }
}
