package com.example.moviesapp.domain

import com.example.moviesapp.data.Movie
import retrofit2.Response

class GetMovieByIdUseCase(private val repo: MoviesRepo) {

    suspend fun execute(id:Int) : Response<Movie> {
        return repo.getMovieById(id)
    }
}