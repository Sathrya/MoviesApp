package com.example.moviesapp.domain

import com.example.moviesapp.data.Movie
import com.example.moviesapp.data.Movies

import retrofit2.Response

interface MoviesRepo {

    suspend fun getLatestMovies() : Response<Movies>

    suspend fun getPopularMovies() : Response<Movies>

    suspend fun getMovieById(id: Int) : Response<Movie>

}