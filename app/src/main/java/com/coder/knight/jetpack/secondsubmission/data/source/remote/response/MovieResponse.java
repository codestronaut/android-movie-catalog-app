package com.coder.knight.jetpack.secondsubmission.data.source.remote.response;

import com.coder.knight.jetpack.secondsubmission.data.source.local.entity.MovieEntity;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MovieResponse {

    @SerializedName("results")
    @Expose
    private ArrayList<MovieEntity> movieList;

    public MovieResponse(ArrayList<MovieEntity> movies) {
        this.movieList = movies;
    }

    public ArrayList<MovieEntity> getMovies() {
        return movieList;
    }
}
