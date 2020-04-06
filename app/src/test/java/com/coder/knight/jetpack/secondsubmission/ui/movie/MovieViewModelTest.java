package com.coder.knight.jetpack.secondsubmission.ui.movie;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.coder.knight.jetpack.secondsubmission.data.source.remote.response.MovieResponse;
import com.coder.knight.jetpack.secondsubmission.networking.repository.ContentRepository;
import com.coder.knight.jetpack.secondsubmission.ui.utils.DummyData;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MovieViewModelTest {
    private static final String API_KEY = "0713407e3664114944df5c45f90c93bb";

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private MovieViewModel movieViewModel;
    private ContentRepository contentRepository = mock(ContentRepository.class);

    @Before
    public void setUp() {
        movieViewModel = new MovieViewModel(contentRepository);
    }

    @Test
    public void getUpComingMovies() {
        MovieResponse dummyMovie = DummyData.generateDummyMovie();
        MutableLiveData<MovieResponse> movies = new MutableLiveData<>();
        movies.setValue(dummyMovie);

        when(contentRepository.getRecommendedMovies(API_KEY)).thenReturn(movies);
        //noinspection unchecked
        Observer<MovieResponse> observer = mock(Observer.class);
        movieViewModel.getRecommendedMovie().observeForever(observer);
        verify(observer).onChanged(dummyMovie);
    }

    @Test
    public void getRecommendedMovies() {
        MovieResponse dummyMovie = DummyData.generateDummyMovie();
        MutableLiveData<MovieResponse> movies = new MutableLiveData<>();
        movies.setValue(dummyMovie);

        when(contentRepository.getRecommendedMovies(API_KEY)).thenReturn(movies);
        //noinspection unchecked
        Observer<MovieResponse> observer = mock(Observer.class);
        movieViewModel.getRecommendedMovie().observeForever(observer);
        verify(observer).onChanged(dummyMovie);
    }
}