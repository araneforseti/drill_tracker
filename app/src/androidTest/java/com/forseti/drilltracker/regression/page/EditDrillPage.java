package com.forseti.drilltracker.regression.page;

import com.forseti.drilltracker.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

public class EditDrillPage {
    int saveButton = android.R.id.button1;
    int drill_name_input = R.id.create_drill_name;

    public void setName(String name) {
        onView(withId(drill_name_input)).perform(replaceText(name));
    }

    public void save() {
        onView(withId(saveButton)).perform(click());
    }
}
