package com.coder.knight.jetpack.secondsubmission.ui.detail;

import android.content.Context;
import android.content.Intent;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import com.coder.knight.jetpack.secondsubmission.R;
import com.coder.knight.jetpack.secondsubmission.data.source.local.entity.MovieEntity;
import com.coder.knight.jetpack.secondsubmission.utils.FakeDummyData;
import com.coder.knight.jetpack.secondsubmission.utils.EspressoIdlingResource;
import com.coder.knight.jetpack.secondsubmission.utils.RecyclerViewItemCountAssertion;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

public class DetailActivityTest {
    private MovieEntity movieEntity = FakeDummyData.generateDummyMovie().get(0);

    @Rule
    public ActivityTestRule<DetailActivity> activityTestRule = new ActivityTestRule<DetailActivity>(DetailActivity.class) {
        @Override
        protected Intent getActivityIntent() {
            Context targetContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
            Intent intent = new Intent(targetContext, DetailActivity.class);
            intent.putExtra(DetailActivity.EXTRA_MOVIE, movieEntity.getMovieId());
            return intent;
        }
    };

    @Before
    public void setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource());
    }

    @After
    public void tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResource());
    }

    @Test
    public void loadMovie() {
        onView(withId(R.id.detail_title_text)).check(matches(isDisplayed()));
        onView(withId(R.id.detail_title_text)).check(matches(withText(movieEntity.getMovieTitle())));
    }

    @Test
    public void loadTrailer() {
        onView(withId(R.id.rv_trailer)).check(new RecyclerViewItemCountAssertion(3));
    }

    @Test
    public void loadReviews() {
        onView(withId(R.id.rv_reviews)).check(new RecyclerViewItemCountAssertion(8));
    }
}