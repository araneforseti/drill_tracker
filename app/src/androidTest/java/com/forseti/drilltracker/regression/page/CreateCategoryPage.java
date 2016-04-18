package com.forseti.drilltracker.regression.page;

import com.forseti.drilltracker.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class CreateCategoryPage {
    int categoryNameInput = R.id.category_name;
    int doneButton = android.R.id.button1;

    public void createCategory(String categoryName) {
        onView(withText("Edit Category")).check(matches(isDisplayed()));
        onView(withId(categoryNameInput)).perform(replaceText(categoryName));
        onView(withId(doneButton)).perform(click());
    }
}
