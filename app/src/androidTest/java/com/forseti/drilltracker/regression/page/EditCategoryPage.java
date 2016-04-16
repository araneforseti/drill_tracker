package com.forseti.drilltracker.regression.page;

import com.forseti.drilltracker.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

public class EditCategoryPage {
    int saveButton = android.R.id.button1;
    int nameField = R.id.category_name;

    public void setName(String changedCategoryName) {
        onView(withId(nameField)).perform(replaceText(changedCategoryName));
    }

    public void save() {
        onView(withId(saveButton)).perform(click());
    }
}
