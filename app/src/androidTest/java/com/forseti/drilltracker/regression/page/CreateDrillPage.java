package com.forseti.drilltracker.regression.page;

import com.forseti.drilltracker.R;
import com.forseti.drilltracker.data.Drill;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

public class CreateDrillPage {
    int drill_name_input = R.id.create_drill_name;
    int drill_description_input = R.id.create_drill_description;
    int drill_instructions_input = R.id.create_drill_instructions;
    int drill_video_input = R.id.create_drill_video_url;

    int doneButton = android.R.id.button1;

    public void createDill(Drill newDrill) {
        onView(withId(drill_name_input)).perform(replaceText(newDrill.getName()));
        onView(withId(drill_description_input)).perform(replaceText(newDrill.getDescription()));
        onView(withId(drill_instructions_input)).perform(replaceText(newDrill.getInstructions()));
        onView(withId(drill_video_input)).perform(replaceText(newDrill.getVideoURL()));

        onView(withId(doneButton)).perform(click());
    }
}
