package com.example.moviesapp.di

import com.example.moviesapp.data.MoviesRepoImpl
import com.example.moviesapp.domain.GetMovieByIdUseCase
import com.example.moviesapp.domain.GetMovieUseCase
import com.example.moviesapp.domain.MoviesRepo
import com.example.moviesapp.service.MovieService
import com.example.moviesapp.ui.viewmodel.LatestMoviesViewModel
import com.example.moviesapp.ui.viewmodel.MovieDetailsViewModel
import com.example.moviesapp.ui.viewmodel.PopularMoviesViewModel
import com.example.moviesapp.utils.ApiHelper
import com.example.moviesapp.utils.Constants
import com.example.moviesapp.utils.Constants.BASE_URL
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

val appModule = module {

    viewModel { LatestMoviesViewModel(get()) }
    viewModel { PopularMoviesViewModel(get()) }

    viewModel {
        MovieDetailsViewModel(
            get()
        )
    }

    factory { GetMovieUseCase(get()) }
    factory { GetMovieByIdUseCase(get()) }

    single<MoviesRepo> { MoviesRepoImpl(get()) }


    factory { provideNetworkApi() }
}

fun provideNetworkApi(): MovieService {
    return ApiHelper(BASE_URL).createRetrofit()
}
