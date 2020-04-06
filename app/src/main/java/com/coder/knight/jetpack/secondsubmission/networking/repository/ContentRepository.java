package com.coder.knight.jetpack.secondsubmission.networking.repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.coder.knight.jetpack.secondsubmission.data.source.local.entity.MovieEntity;
import com.coder.knight.jetpack.secondsubmission.data.source.local.entity.TvShowEntity;
import com.coder.knight.jetpack.secondsubmission.data.source.remote.repository.RemoteRepository;
import com.coder.knight.jetpack.secondsubmission.data.source.remote.response.MovieResponse;
import com.coder.knight.jetpack.secondsubmission.data.source.remote.response.ReviewResponse;
import com.coder.knight.jetpack.secondsubmission.data.source.remote.response.TrailerResponse;
import com.coder.knight.jetpack.secondsubmission.data.source.remote.response.TvShowResponse;

public class ContentRepository {
    private volatile static ContentRepository INSTANCE = null;
    private final RemoteRepository remoteRepository;

    private ContentRepository(@NonNull RemoteRepository remoteRepository) {
        this.remoteRepository = remoteRepository;
    }

    public static ContentRepository getInstance(RemoteRepository remoteData) {
        if (INSTANCE == null) {
            synchronized (ContentRepository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ContentRepository(remoteData);
                }
            }
        }

        return INSTANCE;
    }

    /*
     * Get Upcoming Movies
     * */
    public LiveData<MovieResponse> getUpcomingMovies(String apiKey) {
        final MutableLiveData<MovieResponse> upcomingMovie = new MutableLiveData<>();

        remoteRepository.getUpcomingMovies(apiKey, new RemoteRepository.LoadMovieCallback() {
            @Override
            public void onAllMoviesReceived(MovieResponse movieResponse) {
                if (movieResponse != null) {
                    upcomingMovie.setValue(movieResponse);
                }
            }

            @Override
            public void onDataNotAvailable() {
                // Do nothing . . .
            }
        });
        return upcomingMovie;
    }

    /*
     * Get Recommended Movies
     * */

    public LiveData<MovieResponse> getRecommendedMovies(String apiKey) {
        final MutableLiveData<MovieResponse> recommendedMovie = new MutableLiveData<>();

        remoteRepository.getRecommendedMovie(apiKey, new RemoteRepository.LoadMovieCallback() {
            @Override
            public void onAllMoviesReceived(MovieResponse movieResponse) {
                if (movieResponse != null) {
                    recommendedMovie.setValue(movieResponse);
                }
            }

            @Override
            public void onDataNotAvailable() {
                // Do nothing . . .
            }
        });

        return recommendedMovie;
    }

    /*
     * Get detail movie
     * */

    public LiveData<MovieEntity> getDetailMovie(int id, String apiKey) {
        MutableLiveData<MovieEntity> movieResult = new MutableLiveData<>();

        remoteRepository.getMovieDetail(id, apiKey, new RemoteRepository.LoadMovieDetailCallback() {
            @Override
            public void onDetailLoaded(MovieEntity movieEntity) {
                if (movieEntity != null) {
                    movieResult.setValue(movieEntity);
                }
            }

            @Override
            public void onDataNotAvailable() {
                // Do nothing . . .
            }
        });

        return movieResult;
    }

    /*
     * Get On Air Tv Shows
     * */

    public LiveData<TvShowResponse> getOnAirTvShows(String apiKey) {
        final MutableLiveData<TvShowResponse> onAirTv = new MutableLiveData<>();

        remoteRepository.getOnAirTvShows(apiKey, new RemoteRepository.LoadTvShowCallback() {
            @Override
            public void onAllTvReceived(TvShowResponse tvShowResponse) {
                if (tvShowResponse != null) {
                    onAirTv.setValue(tvShowResponse);
                }
            }

            @Override
            public void onDataNotAvailable() {
                // Do nothing . . .
            }
        });

        return onAirTv;
    }

    /*
     * Get Top Rated Tv Shows
     * */

    public LiveData<TvShowResponse> getTopTvShows(String apiKey) {
        final MutableLiveData<TvShowResponse> topRatedTv = new MutableLiveData<>();

        remoteRepository.getTopTvShows(apiKey, new RemoteRepository.LoadTvShowCallback() {
            @Override
            public void onAllTvReceived(TvShowResponse tvShowResponse) {
                topRatedTv.setValue(tvShowResponse);
            }

            @Override
            public void onDataNotAvailable() {
                // Do nothing . . .
            }
        });

        return topRatedTv;
    }

    /*
     * Get Tv Show Detail
     * */

    public LiveData<TvShowEntity> getTvShowDetail(int id, String apiKey) {
        final MutableLiveData<TvShowEntity> detailTvShow = new MutableLiveData<>();

        remoteRepository.getTvShowDetail(id, apiKey, new RemoteRepository.LoadTvDetailCallback() {
            @Override
            public void onDetailLoaded(TvShowEntity tvShowEntity) {
                if (tvShowEntity != null) {
                    detailTvShow.setValue(tvShowEntity);
                }
            }

            @Override
            public void onDataNotAvailable() {
                // Do nothing . . .
            }
        });

        return detailTvShow;
    }

    public LiveData<TrailerResponse> getTrailers(int id, String apiKey) {
        final MutableLiveData<TrailerResponse> contentTrailer = new MutableLiveData<>();

        remoteRepository.getTrailers(id, apiKey, new RemoteRepository.LoadTrailerCallback() {
            @Override
            public void onAllTrailerReceived(TrailerResponse trailerResponse) {
                if (trailerResponse != null) {
                    contentTrailer.setValue(trailerResponse);
                }
            }

            @Override
            public void onDataNotAvailable() {
                // Do nothing . . .
            }
        });

        return contentTrailer;
    }

    public LiveData<ReviewResponse> getReviews(int id, String apiKey) {
        final MutableLiveData<ReviewResponse> contentReview = new MutableLiveData<>();

        remoteRepository.getReviews(id, apiKey, new RemoteRepository.LoadReviewCallback() {
            @Override
            public void onAllReviewReceived(ReviewResponse reviewResponse) {
                if (reviewResponse != null) {
                    contentReview.setValue(reviewResponse);
                }
            }

            @Override
            public void onDataNotAvailable() {
                // Do nothing . . .
            }
        });

        return contentReview;
    }
}
