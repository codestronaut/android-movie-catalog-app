package com.coder.knight.jetpack.secondsubmission.ui.movie;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.RootMatchers;
import androidx.test.rule.ActivityTestRule;

import com.coder.knight.jetpack.secondsubmission.R;
import com.coder.knight.jetpack.secondsubmission.ui.main.MainActivity;
import com.coder.knight.jetpack.secondsubmission.ui.main.MainActivityTest;
import com.coder.knight.jetpack.secondsubmission.utils.EspressoIdlingResource;
import com.coder.knight.jetpack.secondsubmission.utils.RecyclerViewItemCountAssertion;

import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.Objects;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class MovieFragmentTest {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource());
    }

    @After
    public void tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResource());
    }

    @Test
    public void tabMovieClickTest() {
        // 0 is position of movie's tab
        onView(withId(R.id.main_tabs)).perform(MainActivityTest.selectTabPosition(0));

        onView(withId(R.id.rv_recommended)).check(matches(isDisplayed()));
        onView(withId(R.id.rv_upcoming)).check(matches(isDisplayed()));
    }

    @Test
    public void loadMovies() {
        // Go to movie's tab
        onView(withId(R.id.main_tabs)).perform(MainActivityTest.selectTabPosition(0));

        // Check item count
        onView(withId(R.id.rv_recommended)).check(new RecyclerViewItemCountAssertion(20));
        onView(withId(R.id.rv_upcoming)).check(new RecyclerViewItemCountAssertion(20));
    }

    @Test
    public void recyclerScrollTest() {
        // Go to movie's tab
        onView(withId(R.id.main_tabs)).perform(MainActivityTest.selectTabPosition(0));

        RecyclerView rvMovie1 = activityTestRule.getActivity().findViewById(R.id.rv_recommended);
        RecyclerView rvMovie2 = activityTestRule.getActivity().findViewById(R.id.rv_upcoming);
        int itemCount1 = Objects.requireNonNull(rvMovie1.getAdapter()).getItemCount();
        int itemCount2 = Objects.requireNonNull(rvMovie2.getAdapter()).getItemCount();

        onView(withId(R.id.rv_recommended))
                .inRoot(RootMatchers.withDecorView(
                        Matchers.is(activityTestRule.getActivity().getWindow().getDecorView())
                )).perform(RecyclerViewActions.scrollToPosition(itemCount1 - 1));

        onView(withId(R.id.rv_upcoming))
                .inRoot(RootMatchers.withDecorView(
                        Matchers.is(activityTestRule.getActivity().getWindow().getDecorView())
                )).perform(RecyclerViewActions.scrollToPosition(itemCount2 - 1));
    }
}