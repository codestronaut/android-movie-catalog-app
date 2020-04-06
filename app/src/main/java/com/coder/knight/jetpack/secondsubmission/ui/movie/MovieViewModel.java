package com.coder.knight.jetpack.secondsubmission.ui.movie;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.coder.knight.jetpack.secondsubmission.data.source.remote.response.MovieResponse;
import com.coder.knight.jetpack.secondsubmission.networking.repository.ContentRepository;

public class MovieViewModel extends ViewModel {
    private static final String API_KEY = "0713407e3664114944df5c45f90c93bb";
    private ContentRepository contentRepository;

    public MovieViewModel(ContentRepository contentRepository) {
        this.contentRepository = contentRepository;
    }
    
    LiveData<MovieResponse> getRecommendedMovie() {
        return contentRepository.getRecommendedMovies(API_KEY);
    }

    LiveData<MovieResponse> getUpcomingMovie() {
        return contentRepository.getUpcomingMovies(API_KEY);
    }
}
