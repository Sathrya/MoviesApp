package com.example.moviesapp.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import coil.load
import com.example.moviesapp.R
import com.example.moviesapp.data.Movie
import com.example.moviesapp.data.MovieItem
import com.example.moviesapp.databinding.FragmentMovieDetailsBinding
import com.example.moviesapp.ui.viewmodel.MovieDetailsViewModel
import com.example.moviesapp.utils.Constants
import com.example.moviesapp.utils.GlobalObject
import org.koin.androidx.viewmodel.ext.android.getSharedViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf


class MovieDetailsFragment : Fragment() {

    private lateinit var binding: FragmentMovieDetailsBinding
    private val viewModel : MovieDetailsViewModel by viewModel()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieDetailsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("@@@Please",GlobalObject.getMovieId().toString())
        viewModel.getMovieDetail(GlobalObject.getMovieId())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpObservers()
    }

    private fun setUpObservers() {
        viewModel.detailsMovie.observe(viewLifecycleOwner){
            drawUI(it)
        }
    }

    private fun drawUI(it: Movie?) {

        viewModel.loading.observe(viewLifecycleOwner){
            if (it){
                binding.prgBarMovies.visibility = View.VISIBLE
            }
            else{
                binding.prgBarMovies.visibility = View.GONE
            }
        }
        Log.d("Success","UIIIIIIIII")
        binding.imgMovie.load(Constants.POSTER_BASE_URL + it?.posterPath){
            crossfade(true)
            placeholder(R.drawable.ic_launcher_background)
        }
        binding.tvMovieRuntime.text = "${it?.runtime.toString()}${getString(R.string.minute)}"
        binding.tvMovieTitle.text = it?.title
        binding.tvMovieTagLine.text = it?.tagline
        binding.tvMovieDateRelease.text = it?.releaseDate
        binding.tvMovieRating.text = it?.voteAverage.toString()
        binding.tvMovieRuntime.text = it?.runtime.toString()
        binding.tvMovieBudget.text = it?.budget.toString()
        binding.tvMovieRevenue.text = it?.revenue.toString()
        binding.tvMovieOverview.text = it?.overview
    }

}