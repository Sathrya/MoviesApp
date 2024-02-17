package com.example.moviesapp.utils

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.moviesapp.data.MovieItem
import com.example.moviesapp.domain.MoviesRepo

class MoviePagingSource(private val repo: MoviesRepo) : PagingSource<Int,List<MovieItem>>() {

    override fun getRefreshKey(state: PagingState<Int, List<MovieItem>>): Int? {
        TODO("Not yet implemented")
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, List<MovieItem>> {
        TODO("Not yet implemented")
    }

}