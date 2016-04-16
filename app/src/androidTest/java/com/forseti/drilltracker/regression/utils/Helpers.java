package com.forseti.drilltracker.regression.utils;

import android.view.View;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

public class Helpers {
    public static void checkToastMessage(View view, int string_resource_id) {
        onView(withText(string_resource_id)).inRoot(withDecorView(not(is(view)))).check(matches(isDisplayed()));
    }
}
