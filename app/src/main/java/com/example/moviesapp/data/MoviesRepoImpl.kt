package com.example.moviesapp.data

import com.example.moviesapp.domain.MoviesRepo
import com.example.moviesapp.service.MovieService
import com.example.moviesapp.utils.Constants.API_KEY
import com.example.moviesapp.utils.Constants.TIME_WINDOW
import retrofit2.Response

class MoviesRepoImpl(private val service: MovieService) : MoviesRepo {

    //gets latest movies
    override suspend fun getLatestMovies(): Response<Movies> {
        return service.getLatestMovies(TIME_WINDOW, API_KEY)
    }

    //gets popular movies
    override suspend fun getPopularMovies(): Response<Movies> {
        return service.getPopularMovies(API_KEY)

    }

    //gets single movie details
    override suspend fun getMovieById(id: Int): Response<Movie> {
        return service.getMovieById(id,API_KEY)
    }


}