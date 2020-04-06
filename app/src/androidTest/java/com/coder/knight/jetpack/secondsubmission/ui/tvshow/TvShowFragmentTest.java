package com.coder.knight.jetpack.secondsubmission.ui.tvshow;

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

public class TvShowFragmentTest {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void tabTvShowClickTest() {
        // 1 is position of tv show's tab
        onView(withId(R.id.main_tabs)).perform(MainActivityTest.selectTabPosition(1));
        onView(withId(R.id.rv_on_air)).check(matches(isDisplayed()));
        onView(withId(R.id.rv_top_rated)).check(matches(isDisplayed()));
    }

    @Before
    public void setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource());
    }

    @After
    public void tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResource());
    }

    @Test
    public void loadTvShow() {
        // Go to tv show's tab
        onView(withId(R.id.main_tabs)).perform(MainActivityTest.selectTabPosition(1));

        // Check item count
        onView(withId(R.id.rv_on_air)).check(new RecyclerViewItemCountAssertion(20));
        onView(withId(R.id.rv_top_rated)).check(new RecyclerViewItemCountAssertion(20));
    }

    @Test
    public void recyclerScrollTest() {
        // Go to tv show's tab
        onView(withId(R.id.main_tabs)).perform(MainActivityTest.selectTabPosition(1));

        RecyclerView rvTv1 = activityTestRule.getActivity().findViewById(R.id.rv_on_air);
        RecyclerView rvTv2 = activityTestRule.getActivity().findViewById(R.id.rv_top_rated);
        int itemCount1 = Objects.requireNonNull(rvTv1.getAdapter()).getItemCount();
        int itemCount2 = Objects.requireNonNull(rvTv2.getAdapter()).getItemCount();

        onView(withId(R.id.rv_on_air))
                .inRoot(RootMatchers.withDecorView(
                        Matchers.is(activityTestRule.getActivity().getWindow().getDecorView())
                )).perform(RecyclerViewActions.scrollToPosition(itemCount1 - 1));

        onView(withId(R.id.rv_top_rated))
                .inRoot(RootMatchers.withDecorView(
                        Matchers.is(activityTestRule.getActivity().getWindow().getDecorView())
                )).perform(RecyclerViewActions.scrollToPosition(itemCount2 - 1));
    }
}