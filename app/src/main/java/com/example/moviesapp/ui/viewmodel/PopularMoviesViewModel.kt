package com.example.moviesapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.data.MovieItem
import com.example.moviesapp.domain.GetMovieUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PopularMoviesViewModel(private val useCase: GetMovieUseCase) : ViewModel() {

    val popularMovieList = MutableLiveData<List<MovieItem>>()

    fun getAllPopularMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = useCase.execute("")
            if(response.isSuccessful){
                popularMovieList.postValue(response.body()?.results)
            }
        }
    }
}