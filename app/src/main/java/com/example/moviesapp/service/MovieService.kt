package com.example.moviesapp.service

import com.example.moviesapp.data.Movie
import com.example.moviesapp.data.Movies
import retrofit2.Response
import retrofit2.http.*

interface MovieService {

    @Headers("Content-Type: application/json")
    @GET("trending/movie/{time_window}")
    suspend fun getLatestMovies(
        @Path("time_window") time_window : String,
        @Query("api_key") api_key : String
    ) : Response<Movies>

    @Headers("Content-Type: application/json")
    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") api_key : String
    ) : Response<Movies>

    @Headers("Content-Type: application/json")
    @GET("movie/{movie_id}")
    suspend fun getMovieById(
        @Path("movie_id") movie_id : Int,
        @Query("api_key") api_key : String
    ) : Response<Movie>


}