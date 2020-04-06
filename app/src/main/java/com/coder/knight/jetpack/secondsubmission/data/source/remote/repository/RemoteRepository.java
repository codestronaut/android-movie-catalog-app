package com.coder.knight.jetpack.secondsubmission.data.source.remote.repository;

import androidx.annotation.NonNull;

import com.coder.knight.jetpack.secondsubmission.data.source.local.entity.MovieEntity;
import com.coder.knight.jetpack.secondsubmission.data.source.local.entity.TvShowEntity;
import com.coder.knight.jetpack.secondsubmission.data.source.remote.response.MovieResponse;
import com.coder.knight.jetpack.secondsubmission.data.source.remote.response.ReviewResponse;
import com.coder.knight.jetpack.secondsubmission.data.source.remote.response.TrailerResponse;
import com.coder.knight.jetpack.secondsubmission.data.source.remote.response.TvShowResponse;
import com.coder.knight.jetpack.secondsubmission.networking.ApiInterface;
import com.coder.knight.jetpack.secondsubmission.utils.EspressoIdlingResource;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RemoteRepository {
    private static RemoteRepository INSTANCE;
    private ApiInterface apiInterface;

    private RemoteRepository(ApiInterface apiInterface) {
        this.apiInterface = apiInterface;
    }

    public static RemoteRepository getInstance(ApiInterface apiInterface) {
        if (INSTANCE == null) {
            INSTANCE = new RemoteRepository(apiInterface);
        }
        return INSTANCE;
    }

    public void getRecommendedMovie(String apiKey, LoadMovieCallback callback) {
        EspressoIdlingResource.increment();
        apiInterface.getRecommendedMovies(apiKey).enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(@NonNull Call<MovieResponse> call, @NonNull Response<MovieResponse> response) {
                if (response.isSuccessful()) {
                    callback.onAllMoviesReceived(response.body());
                    EspressoIdlingResource.decrement();
                }
            }

            @Override
            public void onFailure(@NonNull Call<MovieResponse> call, @NonNull Throwable t) {
                callback.onDataNotAvailable();
            }
        });
    }

    public void getUpcomingMovies(String apiKey, LoadMovieCallback callback) {
        EspressoIdlingResource.increment();
        apiInterface.getUpcomingMovies(apiKey).enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(@NonNull Call<MovieResponse> call, @NonNull Response<MovieResponse> response) {
                if (response.isSuccessful()) {
                    callback.onAllMoviesReceived(response.body());
                    EspressoIdlingResource.decrement();
                }
            }

            @Override
            public void onFailure(@NonNull Call<MovieResponse> call, @NonNull Throwable t) {
                callback.onDataNotAvailable();
            }
        });
    }

    public void getMovieDetail(int id, String apiKey, LoadMovieDetailCallback callback) {
        apiInterface.getDetailMovie(id, apiKey).enqueue(new Callback<MovieEntity>() {
            @Override
            public void onResponse(@NonNull Call<MovieEntity> call, @NonNull Response<MovieEntity> response) {
                if (response.isSuccessful()) {
                    callback.onDetailLoaded(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<MovieEntity> call, @NonNull Throwable t) {
                callback.onDataNotAvailable();
                EspressoIdlingResource.decrement();
            }
        });
    }

    public void getOnAirTvShows(String apiKey, LoadTvShowCallback callback) {
        EspressoIdlingResource.increment();
        apiInterface.getOnAirTvShows(apiKey).enqueue(new Callback<TvShowResponse>() {
            @Override
            public void onResponse(@NonNull Call<TvShowResponse> call, @NonNull Response<TvShowResponse> response) {
                if (response.isSuccessful()) {
                    callback.onAllTvReceived(response.body());
                    EspressoIdlingResource.decrement();
                }
            }

            @Override
            public void onFailure(@NonNull Call<TvShowResponse> call, @NonNull Throwable t) {
                callback.onDataNotAvailable();
            }
        });
    }

    public void getTopTvShows(String apiKey, LoadTvShowCallback callback) {
        EspressoIdlingResource.increment();
        apiInterface.getTopTvShows(apiKey).enqueue(new Callback<TvShowResponse>() {
            @Override
            public void onResponse(@NonNull Call<TvShowResponse> call, @NonNull Response<TvShowResponse> response) {
                if (response.isSuccessful()) {
                    callback.onAllTvReceived(response.body());
                    EspressoIdlingResource.decrement();
                }
            }

            @Override
            public void onFailure(@NonNull Call<TvShowResponse> call, @NonNull Throwable t) {
                callback.onDataNotAvailable();
            }
        });
    }

    public void getTvShowDetail(int id, String apiKey, LoadTvDetailCallback callback) {
        apiInterface.getDetailTvShow(id, apiKey).enqueue(new Callback<TvShowEntity>() {
            @Override
            public void onResponse(@NonNull Call<TvShowEntity> call, @NonNull Response<TvShowEntity> response) {
                if (response.isSuccessful()) {
                    callback.onDetailLoaded(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<TvShowEntity> call, @NonNull Throwable t) {
                callback.onDataNotAvailable();
            }
        });
    }

    public void getTrailers(int id, String apiKey, LoadTrailerCallback callback) {
        apiInterface.getTrailer(id, apiKey).enqueue(new Callback<TrailerResponse>() {
            @Override
            public void onResponse(@NonNull Call<TrailerResponse> call, @NonNull Response<TrailerResponse> response) {
                if (response.isSuccessful()) {
                    callback.onAllTrailerReceived(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<TrailerResponse> call, @NonNull Throwable t) {
                callback.onDataNotAvailable();
            }
        });
    }

    public void getReviews(int id, String apiKey, LoadReviewCallback callback) {
        apiInterface.getReviews(id, apiKey).enqueue(new Callback<ReviewResponse>() {
            @Override
            public void onResponse(@NonNull Call<ReviewResponse> call, @NonNull Response<ReviewResponse> response) {
                if (response.isSuccessful()) {
                    callback.onAllReviewReceived(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<ReviewResponse> call, @NonNull Throwable t) {
                callback.onDataNotAvailable();
            }
        });
    }


    // The callbacks
    public interface LoadMovieCallback {
        void onAllMoviesReceived(MovieResponse movieResponse);

        void onDataNotAvailable();
    }

    public interface LoadMovieDetailCallback {
        void onDetailLoaded(MovieEntity movieEntity);

        void onDataNotAvailable();
    }

    public interface LoadTvShowCallback {
        void onAllTvReceived(TvShowResponse tvShowResponse);

        void onDataNotAvailable();
    }

    public interface LoadTvDetailCallback {
        void onDetailLoaded(TvShowEntity tvShowEntity);

        void onDataNotAvailable();
    }

    public interface LoadTrailerCallback {
        void onAllTrailerReceived(TrailerResponse trailerResponse);

        void onDataNotAvailable();
    }

    public interface LoadReviewCallback {
        void onAllReviewReceived(ReviewResponse reviewResponse);

        void onDataNotAvailable();
    }

}
