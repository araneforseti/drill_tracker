package com.forseti.drilltracker.regression.page;

import com.forseti.drilltracker.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.longClick;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class MainPage {
    int addDrillButton = R.id.add_drill;
    int addCategoryButton = R.id.add_category;

    public void openCreateDrillDialog() {
        onView(withId(addDrillButton)).perform(click());
    }

    public void openAddCategoryDialog() {
        onView(withId(addCategoryButton)).perform(click());
    }

    public void deleteCategory(String categoryName) {
        onView(withText(categoryName)).perform(longClick());
        onView(withText(R.string.delete_categoy)).perform(click());
    }

    public void clickCategory(String categoryName) {
        onView(withText(categoryName)).perform(click());
    }

    public int getCategoryPosition(String categoryName) {
        return 0;
    }

    public void deleteDrill(String categoryName, String drillName) {
        onView(withText(drillName)).perform(longClick());
        onView(withText(R.string.delete_drill)).perform(click());
    }
}
