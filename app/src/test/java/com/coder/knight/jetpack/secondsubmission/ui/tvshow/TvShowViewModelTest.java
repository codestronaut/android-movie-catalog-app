package com.coder.knight.jetpack.secondsubmission.ui.tvshow;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.coder.knight.jetpack.secondsubmission.data.source.remote.response.TvShowResponse;
import com.coder.knight.jetpack.secondsubmission.networking.repository.ContentRepository;
import com.coder.knight.jetpack.secondsubmission.ui.utils.DummyData;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TvShowViewModelTest {
    private static final String API_KEY = "0713407e3664114944df5c45f90c93bb";

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private TvShowViewModel tvShowViewModel;
    private ContentRepository contentRepository = mock(ContentRepository.class);

    @Before
    public void setUp() {
        tvShowViewModel = new TvShowViewModel(contentRepository);
    }

    @Test
    public void getOnAirTvShow() {
        TvShowResponse dummyTvShow = DummyData.generateDummyTvShow();
        MutableLiveData<TvShowResponse> tvShow = new MutableLiveData<>();
        tvShow.postValue(dummyTvShow);

        when(contentRepository.getOnAirTvShows(API_KEY)).thenReturn(tvShow);
        //noinspection unchecked
        Observer<TvShowResponse> observer = mock(Observer.class);
        tvShowViewModel.getOnAirTvShows().observeForever(observer);
        verify(observer).onChanged(dummyTvShow);
    }

    @Test
    public void getTopRatedTvShow() {
        TvShowResponse dummyTvShow = DummyData.generateDummyTvShow();
        MutableLiveData<TvShowResponse> tvShow = new MutableLiveData<>();
        tvShow.postValue(dummyTvShow);

        when(contentRepository.getTopTvShows(API_KEY)).thenReturn(tvShow);
        //noinspection unchecked
        Observer<TvShowResponse> observer = mock(Observer.class);
        tvShowViewModel.getTopRatedTvShows().observeForever(observer);
        verify(observer).onChanged(dummyTvShow);
    }

}