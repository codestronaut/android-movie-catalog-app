package com.coder.knight.jetpack.secondsubmission.ui.detail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.coder.knight.jetpack.secondsubmission.data.source.local.entity.MovieEntity;
import com.coder.knight.jetpack.secondsubmission.data.source.local.entity.TvShowEntity;
import com.coder.knight.jetpack.secondsubmission.data.source.remote.response.ReviewResponse;
import com.coder.knight.jetpack.secondsubmission.data.source.remote.response.TrailerResponse;
import com.coder.knight.jetpack.secondsubmission.networking.repository.ContentRepository;

public class DetailViewModel extends ViewModel {
    private static final String API_KEY = "0713407e3664114944df5c45f90c93bb";
    private ContentRepository contentRepository;
    private int contentId;

    public DetailViewModel(ContentRepository contentRepository) {
        this.contentRepository = contentRepository;
    }

    void setContentId(int id) {
        this.contentId = id;
    }

    LiveData<MovieEntity> getMovieDetail() {
        return contentRepository.getDetailMovie(contentId, API_KEY);
    }

    LiveData<TrailerResponse> getTrailer() {
        return contentRepository.getTrailers(contentId, API_KEY);
    }

    LiveData<ReviewResponse> getReview() {
        return contentRepository.getReviews(contentId, API_KEY);
    }

    LiveData<TvShowEntity> getTvShowDetail() {
        return contentRepository.getTvShowDetail(contentId, API_KEY);
    }
}
