package com.example.moviesapp.utils

import com.example.moviesapp.service.MovieService
import com.example.networking.RetrofitHelper

class ApiHelper(
    val baseURl : String
) : RetrofitHelper<MovieService>() {


    override fun createRetrofit(): MovieService {
        return createRetrofit(baseURl).create(MovieService::class.java)
    }

}