package com.example.moviesapp.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.data.Movie
import com.example.moviesapp.domain.GetMovieByIdUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieDetailsViewModel(private val useCase : GetMovieByIdUseCase) : ViewModel() {

    val detailsMovie = MutableLiveData<Movie>()
    val loading = MutableLiveData<Boolean>()

    fun getMovieDetail(id : Int) = viewModelScope.launch(Dispatchers.IO) {
        loading.postValue(true)
        val response = useCase.execute(id)
        if (response.isSuccessful) {
            detailsMovie.postValue(response.body())
        }
        loading.postValue(false)
    }
}