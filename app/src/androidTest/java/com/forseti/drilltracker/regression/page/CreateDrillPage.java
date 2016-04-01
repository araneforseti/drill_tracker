package com.forseti.drilltracker.regression.page;

import com.forseti.drilltracker.R;
import com.forseti.drilltracker.data.Drill;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.core.AllOf.allOf;
import static org.hamcrest.core.Is.is;

public class CreateDrillPage {
    int category_name_input = R.id.categories_spinner;
    int drill_name_input = R.id.create_drill_name;
    int drill_description_input = R.id.create_drill_description;
    int drill_instructions_input = R.id.create_drill_instructions;
    int drill_video_input = R.id.create_drill_video_url;

    int doneButton = android.R.id.button1;

    public void createDill(String categoryName, Drill newDrill) {
        onView(withId(category_name_input)).check(matches(withSpinnerText(containsString(categoryName))));

        onView(withId(drill_name_input)).perform(replaceText(newDrill.getName()));
        onView(withId(drill_description_input)).perform(replaceText(newDrill.getDescription()));
        onView(withId(drill_instructions_input)).perform(replaceText(newDrill.getInstructions()));
        onView(withId(drill_video_input)).perform(replaceText(newDrill.getVideoURL()));

        onView(withId(doneButton)).perform(click());
    }
}
