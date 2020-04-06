package com.coder.knight.jetpack.secondsubmission.networking;

import com.coder.knight.jetpack.secondsubmission.data.source.local.entity.MovieEntity;
import com.coder.knight.jetpack.secondsubmission.data.source.local.entity.TvShowEntity;
import com.coder.knight.jetpack.secondsubmission.data.source.remote.response.MovieResponse;
import com.coder.knight.jetpack.secondsubmission.data.source.remote.response.ReviewResponse;
import com.coder.knight.jetpack.secondsubmission.data.source.remote.response.TrailerResponse;
import com.coder.knight.jetpack.secondsubmission.data.source.remote.response.TvShowResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {
    /*
     * Movies
     * */
    @GET("movie/upcoming")
    Call<MovieResponse> getUpcomingMovies(
            @Query("api_key") String apiKey
    );

    @GET("movie/popular")
    Call<MovieResponse> getRecommendedMovies(
            @Query("api_key") String apiKey
    );

    @GET("movie/{movie_id}")
    Call<MovieEntity> getDetailMovie(
            @Path("movie_id") int id,
            @Query("api_key") String apiKey
    );

    /*
     * Tv Shows
     * */

    @GET("tv/on_the_air")
    Call<TvShowResponse> getOnAirTvShows(
            @Query("api_key") String apiKey
    );

    @GET("tv/top_rated")
    Call<TvShowResponse> getTopTvShows(
            @Query("api_key") String apiKey
    );

    @GET("tv/{id}")
    Call<TvShowEntity> getDetailTvShow(
            @Path("id") int id,
            @Query("api_key") String apiKey
    );

    @GET("movie/{movie_id}/videos")
    Call<TrailerResponse> getTrailer(
            @Path("movie_id") int id,
            @Query("api_key") String apiKey
    );

    @GET("movie/{movie_id}/reviews")
    Call<ReviewResponse> getReviews(
            @Path("movie_id") int id,
            @Query("api_key") String apiKey
    );
}
