package com.coder.knight.jetpack.secondsubmission.data.source.remote.response;

import com.coder.knight.jetpack.secondsubmission.data.source.local.entity.TvShowEntity;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class TvShowResponse {

    @SerializedName("results")
    @Expose
    private ArrayList<TvShowEntity> tvShowList;

    public TvShowResponse(ArrayList<TvShowEntity> tvShows) {
        this.tvShowList = tvShows;
    }

    public ArrayList<TvShowEntity> getTvShows() {
        return tvShowList;
    }
}
