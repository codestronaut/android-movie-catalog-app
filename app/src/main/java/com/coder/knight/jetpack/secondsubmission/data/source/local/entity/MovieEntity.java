package com.coder.knight.jetpack.secondsubmission.data.source.local.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieEntity implements Parcelable {
    @SerializedName("id")
    @Expose
    private int movieId;

    @SerializedName("title")
    @Expose
    private String movieTitle;

    @SerializedName("vote_average")
    @Expose
    private float movieRating;

    @SerializedName("vote_count")
    @Expose
    private int movieVoted;

    @SerializedName("original_language")
    @Expose
    private String movieLanguage;

    @SerializedName("popularity")
    @Expose
    private String moviePopularity;

    @SerializedName("release_date")
    @Expose
    private String movieDate;

    @SerializedName("poster_path")
    @Expose
    private String moviePoster;

    @SerializedName("backdrop_path")
    @Expose
    private String movieBackdrop;

    @SerializedName("overview")
    @Expose
    private String movieDescription;

    @SerializedName("genres")
    @Expose
    private List<GenreEntity> genres;

    public MovieEntity(int movieId, String movieTitle, float movieRating, int movieVoted, String movieLanguage, String moviePopularity, String movieDate, String moviePoster, String movieBackdrop, String movieDescription, List<GenreEntity> genres) {
        this.movieId = movieId;
        this.movieTitle = movieTitle;
        this.movieRating = movieRating;
        this.movieVoted = movieVoted;
        this.movieLanguage = movieLanguage;
        this.moviePopularity = moviePopularity;
        this.movieDate = movieDate;
        this.moviePoster = moviePoster;
        this.movieBackdrop = movieBackdrop;
        this.movieDescription = movieDescription;
        this.genres = genres;
    }

    public int getMovieId() {
        return movieId;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public float getMovieRating() {
        return movieRating;
    }

    public int getMovieVoted() {
        return movieVoted;
    }

    public String getMovieLanguage() {
        return movieLanguage;
    }

    public String getMoviePopularity() {
        return moviePopularity;
    }

    public String getMovieDate() {
        return movieDate;
    }

    public String getMoviePoster() {
        return moviePoster;
    }

    public String getMovieBackdrop() {
        return movieBackdrop;
    }

    public String getMovieDescription() {
        return movieDescription;
    }

    public List<GenreEntity> getGenres() {
        return genres;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.movieId);
        dest.writeString(this.movieTitle);
        dest.writeFloat(this.movieRating);
        dest.writeInt(this.movieVoted);
        dest.writeString(this.movieLanguage);
        dest.writeString(this.moviePopularity);
        dest.writeString(this.movieDate);
        dest.writeString(this.moviePoster);
        dest.writeString(this.movieBackdrop);
        dest.writeString(this.movieDescription);
        dest.writeTypedList(this.genres);
    }

    private MovieEntity(Parcel in) {
        this.movieId = in.readInt();
        this.movieTitle = in.readString();
        this.movieRating = in.readFloat();
        this.movieVoted = in.readInt();
        this.movieLanguage = in.readString();
        this.moviePopularity = in.readString();
        this.movieDate = in.readString();
        this.moviePoster = in.readString();
        this.movieBackdrop = in.readString();
        this.movieDescription = in.readString();
        this.genres = in.createTypedArrayList(GenreEntity.CREATOR);
    }

    public static final Creator<MovieEntity> CREATOR = new Creator<MovieEntity>() {
        @Override
        public MovieEntity createFromParcel(Parcel source) {
            return new MovieEntity(source);
        }

        @Override
        public MovieEntity[] newArray(int size) {
            return new MovieEntity[size];
        }
    };
}
