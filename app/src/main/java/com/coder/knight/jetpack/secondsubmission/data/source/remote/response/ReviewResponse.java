package com.coder.knight.jetpack.secondsubmission.data.source.remote.response;

import com.coder.knight.jetpack.secondsubmission.data.source.local.entity.ReviewEntity;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ReviewResponse {
    @SerializedName("results")
    @Expose
    private List<ReviewEntity> reviews;

    public ReviewResponse(List<ReviewEntity> reviews) {
        this.reviews = reviews;
    }

    public List<ReviewEntity> getReviews() {
        return reviews;
    }
}
