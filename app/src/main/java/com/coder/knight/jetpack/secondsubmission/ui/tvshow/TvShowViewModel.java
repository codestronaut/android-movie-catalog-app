package com.coder.knight.jetpack.secondsubmission.ui.tvshow;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.coder.knight.jetpack.secondsubmission.data.source.remote.response.TvShowResponse;
import com.coder.knight.jetpack.secondsubmission.networking.repository.ContentRepository;

public class TvShowViewModel extends ViewModel {
    private static final String API_KEY = "0713407e3664114944df5c45f90c93bb";
    private ContentRepository contentRepository;

    public TvShowViewModel(ContentRepository contentRepository) {
        this.contentRepository = contentRepository;
    }

    LiveData<TvShowResponse> getOnAirTvShows() {
        return contentRepository.getOnAirTvShows(API_KEY);
    }

    LiveData<TvShowResponse> getTopRatedTvShows() {
        return contentRepository.getTopTvShows(API_KEY);
    }
}
