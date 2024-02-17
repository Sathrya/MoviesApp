package com.example.moviesapp.utils

object GlobalObject {

    var movie_id : Int = 0

    fun setMovieId(id : Int) {
        movie_id = id
    }

    fun getMovieId() = movie_id
}