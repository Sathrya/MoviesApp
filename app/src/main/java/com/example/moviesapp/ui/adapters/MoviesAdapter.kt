package com.example.moviesapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import coil.load
import com.example.moviesapp.R
import com.example.moviesapp.data.MovieItem
import com.example.moviesapp.databinding.MovieItemBinding
import com.example.moviesapp.utils.Constants.POSTER_BASE_URL

class MoviesAdapter(
    private val movieSelected : ((movieId : Int) -> Unit)? = null
) : Adapter<MoviesAdapter.LatestViewHolder>() {

    private var moviesList = listOf<MovieItem>()
    class LatestViewHolder(val binding: MovieItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LatestViewHolder {
        val binding = MovieItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return LatestViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LatestViewHolder, position: Int) {
        holder.binding.tvMovieName.text = moviesList[position].title
        holder.binding.movieReleaseDate.text = moviesList[position].releaseDate
        holder.binding.poster.load( POSTER_BASE_URL + moviesList[position].posterPath){
            crossfade(true)
            placeholder(R.drawable.ic_launcher_background)
        }


        holder.binding.card.setOnClickListener{
            movieSelected?.invoke(
                moviesList[position].id
            )
        }
    }

    override fun getItemCount(): Int {
        return moviesList.size
    }

    fun setItems(items:List<MovieItem>){
        this.moviesList=items
        notifyDataSetChanged()
    }

}