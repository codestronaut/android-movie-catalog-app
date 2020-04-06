package com.coder.knight.jetpack.secondsubmission.ui.detail;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.coder.knight.jetpack.secondsubmission.utils.EspressoIdlingResource;
import com.coder.knight.jetpack.secondsubmission.utils.GlideApp;
import com.bumptech.glide.request.RequestOptions;
import com.coder.knight.jetpack.secondsubmission.R;
import com.coder.knight.jetpack.secondsubmission.data.source.local.entity.GenreEntity;
import com.coder.knight.jetpack.secondsubmission.data.source.local.entity.MovieEntity;
import com.coder.knight.jetpack.secondsubmission.data.source.local.entity.ReviewEntity;
import com.coder.knight.jetpack.secondsubmission.data.source.local.entity.TrailerEntity;
import com.coder.knight.jetpack.secondsubmission.data.source.local.entity.TvShowEntity;
import com.coder.knight.jetpack.secondsubmission.viewmodel.ViewModelFactory;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.DoubleBounce;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {
    private static final String IMG_BASE_URL = "https://image.tmdb.org/t/p/w500";
    private ProgressBar detailProgress;
    private AppBarLayout appBarLayout;
    private NestedScrollView nestedScrollView;
    private ImageView imgBackdrop, imgPoster;
    private TextView tvTitle, tvDate, tvRating, tvLang, tvPopularity, tvVoted, tvOverview, tvGenre;
    private RatingBar detailRatingBar;

    public static final String EXTRA_MOVIE = "extra_movie";
    public static final String EXTRA_TV = "extra_tv";
    int movieId;
    int tvShowId;

    // Trailer
    private RecyclerView rvTrailer;
    private TrailerAdapter trailerAdapter;

    // Review
    private RecyclerView rvReviews;
    private ReviewAdapter reviewAdapter;

    private DetailViewModel viewModel;
    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsingToolbarLayout;

    @NonNull
    private static DetailViewModel obtainViewModel(AppCompatActivity activity) {
        ViewModelFactory factory = ViewModelFactory.getInstance();
        return ViewModelProviders.of(activity, factory).get(DetailViewModel.class);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        initViews();
        setUpLoadingBar();

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow);
        }

        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);
        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));

        viewModel = obtainViewModel(this);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            movieId = bundle.getInt(EXTRA_MOVIE);
            if (movieId != 0) {
                viewModel.setContentId(movieId);
            }

            tvShowId = bundle.getInt(EXTRA_TV);
            if (tvShowId != 0) {
                viewModel.setContentId(tvShowId);
            }
        }

        detailProgress.setVisibility(View.VISIBLE);
        appBarLayout.setVisibility(View.GONE);
        nestedScrollView.setVisibility(View.GONE);

        EspressoIdlingResource.increment();
        viewModel.getMovieDetail().observe(this, movieEntity -> {
            if (movieEntity != null) {
                detailProgress.setVisibility(View.GONE);
                appBarLayout.setVisibility(View.VISIBLE);
                nestedScrollView.setVisibility(View.VISIBLE);
                EspressoIdlingResource.decrement();
                populateMovies(movieEntity);
                getMovieGenre(movieEntity);
                getMovieTrailer();
                getMovieReview();
            }
        });

        viewModel.getTvShowDetail().observe(this, tvShowEntity -> {
            if (tvShowEntity != null) {
                populateTvShows(tvShowEntity);
                getTvShowGenre(tvShowEntity);
                getTvTrailer();
                getTvReview();
            }
        });
    }

    /*
     * Init the views
     * */
    private void initViews() {
        detailProgress = findViewById(R.id.detail_progress);
        appBarLayout = findViewById(R.id.app_bar);
        nestedScrollView = findViewById(R.id.nested_scroll);
        toolbar = findViewById(R.id.detail_toolbar);
        collapsingToolbarLayout = findViewById(R.id.detail_collapse_toolbar);
        imgBackdrop = findViewById(R.id.detail_backdrop_image);
        imgPoster = findViewById(R.id.detail_poster_image);
        tvDate = findViewById(R.id.detail_date_text);
        tvTitle = findViewById(R.id.detail_title_text);
        tvRating = findViewById(R.id.detail_rating_text);
        tvLang = findViewById(R.id.detail_lang_text);
        tvPopularity = findViewById(R.id.detail_popularity_text);
        tvVoted = findViewById(R.id.detail_voted_text);
        tvOverview = findViewById(R.id.detail_overview_text);
        tvGenre = findViewById(R.id.detail_genre_text);
        detailRatingBar = findViewById(R.id.detail_rating_bar);
        rvTrailer = findViewById(R.id.rv_trailer);
        rvReviews = findViewById(R.id.rv_reviews);
    }

    private void setUpLoadingBar() {
        Sprite doubleBounce = new DoubleBounce();
        detailProgress.setIndeterminateDrawable(doubleBounce);
    }

    private void populateMovies(MovieEntity movieEntity) {
        collapsingToolbarLayout.setTitle(movieEntity.getMovieTitle());
        tvTitle.setText(movieEntity.getMovieTitle());
        tvDate.setText(movieEntity.getMovieDate().split("-")[0]);
        tvRating.setText(String.valueOf(movieEntity.getMovieRating() / 2));
        tvLang.setText(movieEntity.getMovieLanguage());
        tvPopularity.setText(movieEntity.getMoviePopularity());
        tvVoted.setText(String.valueOf(movieEntity.getMovieVoted()));
        tvOverview.setText(movieEntity.getMovieDescription());
        getMovieGenre(movieEntity);
        detailRatingBar.setRating(movieEntity.getMovieRating() / 2);
        GlideApp.with(this)
                .load(IMG_BASE_URL + movieEntity.getMoviePoster())
                .apply(new RequestOptions().override(1920, 1080))
                .into(imgPoster);

        GlideApp.with(this)
                .load(IMG_BASE_URL + movieEntity.getMovieBackdrop())
                .apply(new RequestOptions().override(1920, 1080))
                .into(imgBackdrop);
    }

    private void populateTvShows(TvShowEntity tvShowEntity) {
        collapsingToolbarLayout.setTitle(tvShowEntity.getTvTitle());
        tvTitle.setText(tvShowEntity.getTvTitle());
        tvDate.setText(tvShowEntity.getTvDate().split("-")[0]);
        tvRating.setText(String.valueOf(tvShowEntity.getTvRating() / 2));
        tvLang.setText(tvShowEntity.getTvLanguage());
        tvPopularity.setText(tvShowEntity.getTvPopularity());
        tvVoted.setText(String.valueOf(tvShowEntity.getTvVoted()));
        tvOverview.setText(tvShowEntity.getTvDescription());
        getTvShowGenre(tvShowEntity);
        detailRatingBar.setRating(tvShowEntity.getTvRating() / 2);
        GlideApp.with(this)
                .load(IMG_BASE_URL + tvShowEntity.getTvPoster())
                .apply(new RequestOptions().override(1920, 1080))
                .into(imgPoster);
        GlideApp.with(this)
                .load(IMG_BASE_URL + tvShowEntity.getTvBackdrop())
                .apply(new RequestOptions().override(1920, 1080))
                .into(imgBackdrop);
    }

    /*
     * Get trailer base on id
     * */
    private void getMovieTrailer() {
        EspressoIdlingResource.increment();
        viewModel.getTrailer().observe(this, trailerResponse -> {
            detailProgress.setVisibility(View.GONE);
            EspressoIdlingResource.decrement();
            List<TrailerEntity> trailerEntities = trailerResponse.getTrailers();
            trailerAdapter.setTrailer(trailerEntities);
            trailerAdapter.notifyDataSetChanged();
        });

        setUpRvTrailer();
    }

    private void getTvTrailer() {
        viewModel.getTrailer().observe(this, trailerResponse -> {
            detailProgress.setVisibility(View.GONE);
            List<TrailerEntity> trailerEntities = trailerResponse.getTrailers();
            trailerAdapter.setTrailer(trailerEntities);
            trailerAdapter.notifyDataSetChanged();
        });

        setUpRvTrailer();
    }

    private void setUpRvTrailer() {
        trailerAdapter = new TrailerAdapter(this);
        rvTrailer.setHasFixedSize(true);
        rvTrailer.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rvTrailer.setAdapter(trailerAdapter);
    }

    /*
     * Get Review
     * */
    private void getMovieReview() {
        EspressoIdlingResource.increment();
        viewModel.getReview().observe(this, reviewResponse -> {
            detailProgress.setVisibility(View.GONE);
            EspressoIdlingResource.decrement();
            List<ReviewEntity> reviewEntities = reviewResponse.getReviews();
            reviewAdapter.setReview(reviewEntities);
            reviewAdapter.notifyDataSetChanged();
        });

        setUpRvReview();
    }

    private void getTvReview() {
        viewModel.getReview().observe(this, reviewResponse -> {
            detailProgress.setVisibility(View.GONE);
            List<ReviewEntity> reviewEntities = reviewResponse.getReviews();
            reviewAdapter.setReview(reviewEntities);
            reviewAdapter.notifyDataSetChanged();
        });

        setUpRvReview();
    }

    private void setUpRvReview() {
        reviewAdapter = new ReviewAdapter();
        rvReviews.setHasFixedSize(true);
        rvReviews.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rvReviews.setAdapter(reviewAdapter);
    }


    /*
     * Get Movie & Tv Genre
     * */
    private void getMovieGenre(final MovieEntity movie) {
        if (movie.getGenres() != null) {
            List<String> currentGenres = new ArrayList<>();
            for (GenreEntity genre : movie.getGenres()) {
                currentGenres.add(genre.getName());
            }
            tvGenre.setText(TextUtils.join(", ", currentGenres));
        }
    }

    private void getTvShowGenre(final TvShowEntity tvShow) {
        if (tvShow.getGenres() != null) {
            List<String> currentGenres = new ArrayList<>();
            for (GenreEntity genre : tvShow.getGenres()) {
                currentGenres.add(genre.getName());
            }
            tvGenre.setText(TextUtils.join(", ", currentGenres));
        }
    }
}
