package com.example.moviesapp.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.data.MovieItem
import com.example.moviesapp.domain.GetMovieUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LatestMoviesViewModel(private val useCase: GetMovieUseCase) : ViewModel() {

    val latestMovieList = MutableLiveData<List<MovieItem>>()

    fun getAllLatestMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = useCase.execute("latest")
            Log.d("Success",response.code().toString())
            latestMovieList.postValue(response.body()?.results)
        }
    }
}