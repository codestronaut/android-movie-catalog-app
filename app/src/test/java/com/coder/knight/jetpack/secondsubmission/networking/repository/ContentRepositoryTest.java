package com.coder.knight.jetpack.secondsubmission.networking.repository;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.coder.knight.jetpack.secondsubmission.data.source.local.entity.MovieEntity;
import com.coder.knight.jetpack.secondsubmission.data.source.local.entity.TvShowEntity;
import com.coder.knight.jetpack.secondsubmission.data.source.remote.repository.RemoteRepository;
import com.coder.knight.jetpack.secondsubmission.data.source.remote.response.MovieResponse;
import com.coder.knight.jetpack.secondsubmission.data.source.remote.response.ReviewResponse;
import com.coder.knight.jetpack.secondsubmission.data.source.remote.response.TrailerResponse;
import com.coder.knight.jetpack.secondsubmission.data.source.remote.response.TvShowResponse;
import com.coder.knight.jetpack.secondsubmission.networking.FakeContentRepository;
import com.coder.knight.jetpack.secondsubmission.ui.utils.DummyData;
import com.coder.knight.jetpack.secondsubmission.ui.utils.LiveDataTestUtils;

import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class ContentRepositoryTest {
    private static final String API_KEY = "0713407e3664114944df5c45f90c93bb";

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private RemoteRepository remoteRepository = mock(RemoteRepository.class);
    private FakeContentRepository contentRepository = new FakeContentRepository(remoteRepository);
    private MovieResponse movieResponse = DummyData.generateDummyMovie();
    private int movieId = movieResponse.getMovies().get(0).getMovieId();
    private MovieEntity movieEntity = DummyData.generateMovieById(movieId);
    private TvShowResponse tvShowResponse = DummyData.generateDummyTvShow();
    private int tvShowId = tvShowResponse.getTvShows().get(0).getTvId();
    private TvShowEntity tvShowEntity = DummyData.generateTvShowById(tvShowId);
    private TrailerResponse trailerResponse = DummyData.generateTrailer();
    private ReviewResponse reviewResponse = DummyData.generateReviews();

    /*
     * Get recommended movie list test
     * */
    @Test
    public void getRecommendedMovies() {
        doAnswer(invocation -> {
            ((RemoteRepository.LoadMovieCallback) invocation.getArguments()[1])
                    .onAllMoviesReceived(movieResponse);
            return null;
        }).when(remoteRepository).getRecommendedMovie(eq(API_KEY), any(RemoteRepository.LoadMovieCallback.class));

        MovieResponse response = LiveDataTestUtils.getValue(contentRepository.getRecommendedMovies(API_KEY));
        verify(remoteRepository, times(1)).getRecommendedMovie(eq(API_KEY),
                any(RemoteRepository.LoadMovieCallback.class));
        assertNotNull(response);
        assertEquals(movieResponse.getMovies().size(), response.getMovies().size());
    }

    /*
     * Get upcoming movie list test
     * */
    @Test
    public void getUpcomingMovies() {
        doAnswer(invocation -> {
            ((RemoteRepository.LoadMovieCallback) invocation.getArguments()[1])
                    .onAllMoviesReceived(movieResponse);
            return null;
        }).when(remoteRepository).getUpcomingMovies(eq(API_KEY), any(RemoteRepository.LoadMovieCallback.class));

        MovieResponse response = LiveDataTestUtils.getValue(contentRepository.getUpcomingMovies(API_KEY));
        verify(remoteRepository, times(1)).getUpcomingMovies(eq(API_KEY),
                any(RemoteRepository.LoadMovieCallback.class));
        assertNotNull(response);
        assertEquals(movieResponse.getMovies().size(), response.getMovies().size());
    }

    /*
     * Get movie detail base on movie id
     * Verify that detail content is relevant with movie id
     * */
    @Test
    public void getMovieDetail() {
        doAnswer(invocation -> {
            ((RemoteRepository.LoadMovieDetailCallback) invocation.getArguments()[2])
                    .onDetailLoaded(movieEntity);
            return null;
        }).when(remoteRepository).getMovieDetail(eq(movieId), eq(API_KEY), any(RemoteRepository.LoadMovieDetailCallback.class));

        MovieEntity resultMovie = LiveDataTestUtils.getValue(contentRepository.getDetailMovie(movieId, API_KEY));
        verify(remoteRepository, times(1))
                .getMovieDetail(eq(movieId), eq(API_KEY), any(RemoteRepository.LoadMovieDetailCallback.class));
        assertNotNull(resultMovie);
        assertEquals(movieEntity.getMovieTitle(), resultMovie.getMovieTitle());
    }

    /*
     * Get on air tv show list test
     * */
    @Test
    public void getOnAirTvShow() {
        doAnswer(invocation -> {
            ((RemoteRepository.LoadTvShowCallback) invocation.getArguments()[1])
                    .onAllTvReceived(tvShowResponse);
            return null;
        }).when(remoteRepository).getOnAirTvShows(eq(API_KEY), any(RemoteRepository.LoadTvShowCallback.class));

        TvShowResponse response = LiveDataTestUtils.getValue(contentRepository.getOnAirTvShows(API_KEY));
        verify(remoteRepository, times(1)).getOnAirTvShows(eq(API_KEY),
                any(RemoteRepository.LoadTvShowCallback.class));
        assertNotNull(response);
        assertEquals(tvShowResponse.getTvShows().size(), response.getTvShows().size());
    }

    /*
     * Get top tv show list test
     * */
    @Test
    public void getTopRatedTvShow() {
        doAnswer(invocation -> {
            ((RemoteRepository.LoadTvShowCallback) invocation.getArguments()[1])
                    .onAllTvReceived(tvShowResponse);
            return null;
        }).when(remoteRepository).getTopTvShows(eq(API_KEY), any(RemoteRepository.LoadTvShowCallback.class));

        TvShowResponse response = LiveDataTestUtils.getValue(contentRepository.getTopTvShows(API_KEY));
        verify(remoteRepository, times(1)).getTopTvShows(eq(API_KEY),
                any(RemoteRepository.LoadTvShowCallback.class));
        assertEquals(tvShowResponse.getTvShows().size(), response.getTvShows().size());
    }

    /*
     * Get tv show detail base on tv show id
     * Verify that detail content relevant with tv show id
     * */
    @Test
    public void getTvShowDetail() {
        doAnswer(invocation -> {
            ((RemoteRepository.LoadTvDetailCallback) invocation.getArguments()[2])
                    .onDetailLoaded(tvShowEntity);
            return null;
        }).when(remoteRepository).getTvShowDetail(eq(tvShowId), eq(API_KEY), any(RemoteRepository.LoadTvDetailCallback.class));

        TvShowEntity resultTvShow = LiveDataTestUtils.getValue(contentRepository.getTvShowDetail(tvShowId, API_KEY));
        verify(remoteRepository, times(1)).getTvShowDetail(eq(tvShowId),
                eq(API_KEY),
                any(RemoteRepository.LoadTvDetailCallback.class));
        assertNotNull(remoteRepository);
        assertEquals(tvShowEntity.getTvTitle(), resultTvShow.getTvTitle());
    }

    /*
     * Get trailer test
     * I use movie id for testing
     * verify that trailer key is relevant with the id
     * */
    @Test
    public void generateTrailer() {
        doAnswer(invocation -> {
            ((RemoteRepository.LoadTrailerCallback) invocation.getArguments()[2])
                    .onAllTrailerReceived(trailerResponse);
            return null;
        }).when(remoteRepository).getTrailers(eq(movieId), eq(API_KEY), any(RemoteRepository.LoadTrailerCallback.class));

        TrailerResponse trailerResult = LiveDataTestUtils.getValue(contentRepository.getTrailers(movieId, API_KEY));
        verify(remoteRepository, times(1)).getTrailers(eq(movieId),
                eq(API_KEY), any(RemoteRepository.LoadTrailerCallback.class));
        assertNotNull(trailerResult);
        assertEquals(trailerResponse.getTrailers().get(0).getKey(), trailerResult.getTrailers().get(0).getKey());
    }

    @Test
    public void generateReview() {
        doAnswer(invocation -> {
            ((RemoteRepository.LoadReviewCallback) invocation.getArguments()[2])
                    .onAllReviewReceived(reviewResponse);
            return null;
        }).when(remoteRepository).getReviews(eq(movieId), eq(API_KEY), any(RemoteRepository.LoadReviewCallback.class));

        ReviewResponse reviewResult = LiveDataTestUtils.getValue(contentRepository.getReviews(movieId, API_KEY));
        verify(remoteRepository, times(1)).getReviews(eq(movieId),
                eq(API_KEY), any(RemoteRepository.LoadReviewCallback.class));
        assertNotNull(reviewResult);
        assertEquals(reviewResponse.getReviews().get(0).getAuthor(), reviewResult.getReviews().get(0).getAuthor());
    }
}