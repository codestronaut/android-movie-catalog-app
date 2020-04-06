package com.coder.knight.jetpack.secondsubmission.ui.detail;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.coder.knight.jetpack.secondsubmission.data.source.local.entity.MovieEntity;
import com.coder.knight.jetpack.secondsubmission.data.source.remote.response.MovieResponse;
import com.coder.knight.jetpack.secondsubmission.data.source.remote.response.ReviewResponse;
import com.coder.knight.jetpack.secondsubmission.data.source.remote.response.TrailerResponse;
import com.coder.knight.jetpack.secondsubmission.networking.repository.ContentRepository;
import com.coder.knight.jetpack.secondsubmission.ui.utils.DummyData;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DetailViewModelTest {
    private static final String API_KEY = "0713407e3664114944df5c45f90c93bb";

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private DetailViewModel detailViewModel;
    private ContentRepository contentRepository = mock(ContentRepository.class);
    private MovieResponse movieResponse = DummyData.generateDummyMovie();
    private MovieEntity movieEntity = movieResponse.getMovies().get(0);
    private int movieId = movieEntity.getMovieId();
    private TrailerResponse trailerResponse = DummyData.generateTrailer();
    private ReviewResponse reviewResponse = DummyData.generateReviews();

    @Before
    public void setUp() {
        detailViewModel = new DetailViewModel(contentRepository);
        detailViewModel.setContentId(movieId);
    }

    @Test
    public void getDetailMovie() {
        MutableLiveData<MovieEntity> movieEntities = new MutableLiveData<>();
        movieEntities.postValue(movieEntity);

        when(contentRepository.getDetailMovie(movieId, API_KEY)).thenReturn(movieEntities);
        //noinspection unchecked
        Observer<MovieEntity> observer = mock(Observer.class);
        detailViewModel.getMovieDetail().observeForever(observer);
        verify(observer).onChanged(movieEntity);
    }

    @Test
    public void getTrailer() {
        MutableLiveData<TrailerResponse> trailer = new MutableLiveData<>();
        trailer.postValue(trailerResponse);

        when(contentRepository.getTrailers(movieId, API_KEY)).thenReturn(trailer);
        //noinspection unchecked
        Observer<TrailerResponse> observer = mock(Observer.class);
        detailViewModel.getTrailer().observeForever(observer);
        verify(observer).onChanged(trailerResponse);
    }

    @Test
    public void getReview() {
        MutableLiveData<ReviewResponse> review = new MutableLiveData<>();
        review.postValue(reviewResponse);

        when(contentRepository.getReviews(movieId, API_KEY)).thenReturn(review);
        //noinspection unchecked
        Observer<ReviewResponse> observer = mock(Observer.class);
        detailViewModel.getReview().observeForever(observer);
        verify(observer).onChanged(reviewResponse);
    }

}