package com.example.movieguide.Interface;

import com.example.movieguide.Model.MovieCredits;
import com.example.movieguide.Model.MovieDetails;
import com.example.movieguide.Model.MovieImages;
import com.example.movieguide.Model.MovieResponse;
import com.example.movieguide.Model.MovieVideos;
import com.example.movieguide.Model.PersonDetails;
import com.example.movieguide.Model.PersonImages;
import com.example.movieguide.Model.PersonResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitService {


    @GET("search/movie")
    Call<MovieResponse> getMoviesByQuery(@Query("api_key") String api_key, @Query("query") String query);

    @GET("movie/{movie_id}")
    Call<MovieDetails> getMovieDetailsById(@Path("movie_id") int movie_id, @Query("api_key") String api_key);

    @GET("movie/{movie_id}/credits")
    Call<MovieCredits> getMovieCreditsById(@Path("movie_id") int movie_id, @Query("api_key") String api_key);

    @GET("movie/{movie_id}/images")
    Call<MovieImages> getMovieImagesById(@Path("movie_id") int movie_id, @Query("api_key") String api_key);

    @GET("movie/{movie_id}/videos")
    Call<MovieVideos> getMovieVideosById(@Path("movie_id") int movie_id, @Query("api_key") String api_key);

    @GET("search/person")
    Call<PersonResponse> getPersonsByQuery(@Query("api_key") String api_key, @Query("query") String query);

    @GET("person/{person_id}")
    Call<PersonDetails> getPersonDetailsById(@Path("person_id") int person_id, @Query("api_key") String api_key);

        //create a service to get the images
    @GET("person/{person_id}/images")
    Call<PersonImages> getPersonImagesById(@Path("person_id") int person_id, @Query("api_key") String api_key);




}
