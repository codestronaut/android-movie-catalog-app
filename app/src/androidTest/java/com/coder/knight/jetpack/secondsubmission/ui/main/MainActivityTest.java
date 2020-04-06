package com.coder.knight.jetpack.secondsubmission.ui.main;

import android.view.View;

import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.rule.ActivityTestRule;

import com.coder.knight.jetpack.secondsubmission.R;
import com.google.android.material.tabs.TabLayout;

import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.swipeLeft;
import static androidx.test.espresso.action.ViewActions.swipeRight;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.core.AllOf.allOf;
import static org.junit.Assert.*;
import static org.hamcrest.core.IsNull.notNullValue;

public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);

    /*
     * Preparation
     * */
    @Before
    public void setUp() {
        MainActivity mainActivity = activityTestRule.getActivity();
        assertThat(mainActivity, notNullValue());
    }

    /*
     * Set up tab layout
     * */
    @Test
    public void setUpTabLayout() {
        onView(withId(R.id.main_tabs)).check(matches(isDisplayed()));
    }

    public static ViewAction selectTabPosition(final int position) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return allOf(isDisplayed(), isAssignableFrom(TabLayout.class));
            }

            @Override
            public String getDescription() {
                return "Tab opened at index: " + position;
            }

            @Override
            public void perform(UiController uiController, View view) {
                if (view instanceof TabLayout) {
                    TabLayout tabs = (TabLayout) view;
                    TabLayout.Tab tabPosition = tabs.getTabAt(position);

                    if (tabPosition != null) {
                        tabPosition.select();
                    }
                }
            }
        };
    }

    @Test
    public void switchTab() {
        /*
         * Index 0 is Movie Fragment
         * Index 1 is Tv Show Fragment
         * */
        onView(withId(R.id.main_tabs)).perform(selectTabPosition(1));
        onView(withId(R.id.main_tabs)).perform(selectTabPosition(0));
    }

    @Test
    public void swipePage() {
        onView(withId(R.id.main_view_pager)).check(matches(isDisplayed()));

        // swipe left
        onView(withId(R.id.main_view_pager)).perform(swipeLeft());

        // swipe right
        onView(withId(R.id.main_view_pager)).perform(swipeRight());
    }

}