package com.coder.knight.jetpack.secondsubmission.data.source.local.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TvShowEntity implements Parcelable {

    @SerializedName("id")
    @Expose
    private int tvId;

    @SerializedName("name")
    @Expose
    private String tvTitle;

    @SerializedName("popularity")
    @Expose
    private String tvPopularity;

    @SerializedName("vote_count")
    @Expose
    private int tvVoted;

    @SerializedName("vote_average")
    @Expose
    private float tvRating;

    @SerializedName("first_air_date")
    @Expose
    private String tvDate;

    @SerializedName("poster_path")
    @Expose
    private String tvPoster;

    @SerializedName("backdrop_path")
    @Expose
    private String tvBackdrop;

    @SerializedName("original_language")
    @Expose
    private String tvLanguage;

    @SerializedName("overview")
    @Expose
    private String tvDescription;

    @SerializedName("genres")
    @Expose
    private List<GenreEntity> genres;

    public int getTvId() {
        return tvId;
    }

    public String getTvTitle() {
        return tvTitle;
    }

    public String getTvPopularity() {
        return tvPopularity;
    }

    public int getTvVoted() {
        return tvVoted;
    }

    public float getTvRating() {
        return tvRating;
    }

    public String getTvDate() {
        return tvDate;
    }

    public String getTvPoster() {
        return tvPoster;
    }

    public String getTvBackdrop() {
        return tvBackdrop;
    }

    public String getTvLanguage() {
        return tvLanguage;
    }

    public String getTvDescription() {
        return tvDescription;
    }

    public List<GenreEntity> getGenres() {
        return genres;
    }

    public TvShowEntity(int tvId, String tvTitle, String tvPopularity, int tvVoted, float tvRating, String tvDate, String tvPoster, String tvBackdrop, String tvLanguage, String tvDescription, List<GenreEntity> genres) {
        this.tvId = tvId;
        this.tvTitle = tvTitle;
        this.tvPopularity = tvPopularity;
        this.tvVoted = tvVoted;
        this.tvRating = tvRating;
        this.tvDate = tvDate;
        this.tvPoster = tvPoster;
        this.tvBackdrop = tvBackdrop;
        this.tvLanguage = tvLanguage;
        this.tvDescription = tvDescription;
        this.genres = genres;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.tvId);
        dest.writeString(this.tvTitle);
        dest.writeString(this.tvPopularity);
        dest.writeInt(this.tvVoted);
        dest.writeFloat(this.tvRating);
        dest.writeString(this.tvDate);
        dest.writeString(this.tvPoster);
        dest.writeString(this.tvBackdrop);
        dest.writeString(this.tvLanguage);
        dest.writeString(this.tvDescription);
        dest.writeTypedList(this.genres);
    }

    private TvShowEntity(Parcel in) {
        this.tvId = in.readInt();
        this.tvTitle = in.readString();
        this.tvPopularity = in.readString();
        this.tvVoted = in.readInt();
        this.tvRating = in.readFloat();
        this.tvDate = in.readString();
        this.tvPoster = in.readString();
        this.tvBackdrop = in.readString();
        this.tvLanguage = in.readString();
        this.tvDescription = in.readString();
        this.genres = in.createTypedArrayList(GenreEntity.CREATOR);
    }

    public static final Creator<TvShowEntity> CREATOR = new Creator<TvShowEntity>() {
        @Override
        public TvShowEntity createFromParcel(Parcel source) {
            return new TvShowEntity(source);
        }

        @Override
        public TvShowEntity[] newArray(int size) {
            return new TvShowEntity[size];
        }
    };
}
