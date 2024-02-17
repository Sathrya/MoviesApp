package com.example.moviesapp.domain

import com.example.moviesapp.data.Movie
import com.example.moviesapp.data.Movies
import com.example.moviesapp.utils.Constants.MOVIE_LATEST
import retrofit2.Response

class GetMovieUseCase(private val repo: MoviesRepo) {

    suspend fun execute(type : String = ""): Response<Movies> {
        if(type == MOVIE_LATEST) {
            return repo.getLatestMovies()
        }
        else {
            return repo.getPopularMovies()
        }

    }
}