package com.forseti.drilltracker.regression.page;

import com.forseti.drilltracker.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.longClick;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class MainPage {
    int addDrillButton = R.id.add_drill;
    int addCategoryButton = R.id.add_category;
    int menuButton = R.id.menu;

    public void openMenu() {
        onView(withId(menuButton)).perform(click());
    }

    public void openCreateDrillDialog() {
        openMenu();
        onView(withId(addDrillButton)).check(matches(isDisplayed()));
        onView(withId(addDrillButton)).perform(click());
        onView(withId(addDrillButton)).perform(longClick());
    }

    public void openAddCategoryDialog() {
        openMenu();
        onView(withId(addCategoryButton)).check(matches(isDisplayed()));
        onView(withId(addCategoryButton)).perform(click());
        onView(withId(addCategoryButton)).perform(longClick());
    }

    public void deleteCategory(String categoryName) {
        onView(withText(categoryName)).perform(longClick());
        onView(withText(R.string.delete_categoy)).perform(click());
    }

    public void clickCategory(String categoryName) {
        onView(withText(categoryName)).perform(click());
    }

    public void deleteDrill(String drillName) {
        onView(withText(drillName)).perform(longClick());
        onView(withText(R.string.delete_drill)).perform(click());
    }

    public void openEditDrillDialog(String drillName) {
        onView(withText(drillName)).perform(longClick());
        onView(withText(R.string.edit_drill)).perform(click());
    }

    public void openEditCategory(String categoryName) {
        onView(withText(categoryName)).perform(longClick());
        onView(withText(R.string.edit_categoy)).perform(click());
    }
}
