package com.example.moviesapp.ui.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.moviesapp.R
import com.example.moviesapp.data.MovieItem
import com.example.moviesapp.databinding.FragmentLatestMovieBinding
import com.example.moviesapp.ui.MoviesDetailsActivity
import com.example.moviesapp.ui.adapters.MoviesAdapter
import com.example.moviesapp.ui.viewmodel.LatestMoviesViewModel
import com.example.moviesapp.utils.GlobalObject
import com.example.moviesapp.utils.NetworkHelper
import org.koin.androidx.viewmodel.ext.android.viewModel

class LatestMoviesFragment : Fragment() {

    private lateinit var binding: FragmentLatestMovieBinding
    private val thisViewModel : LatestMoviesViewModel by viewModel()
    private lateinit var mAdapter : MoviesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLatestMovieBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        if (NetworkHelper.checkConnectivity(requireActivity())){
            binding.noInternetBanner.visibility = View.GONE
            thisViewModel.getAllLatestMovies()
            setUpObservers()
        }
        else{
            showNoInternet()
        }
    }

    private fun showNoInternet() {
        binding.mainLoader.visibility = View.GONE
        Toast.makeText(requireContext(), R.string.checkInternet,Toast.LENGTH_SHORT).show()
        if (thisViewModel.latestMovieList.value?.isEmpty() == true) {
            binding.noInternetBanner.visibility = View.VISIBLE
        }
    }

    private fun setUpObservers() {
        thisViewModel.latestMovieList.observe(viewLifecycleOwner){
            initViews(it!!)
        }
    }

    private fun initViews(list: List<MovieItem>) {
        mAdapter = MoviesAdapter( this :: onMovieCardClicked)
        if (list.isNotEmpty()){
            binding.mainLoader.visibility = View.GONE
        }
        else {
            binding.mainLoader.visibility = View.VISIBLE
        }

        mAdapter.setItems(list)
        binding.latestMoviesContainer.apply {
            layoutManager = GridLayoutManager(requireContext(),2)
            adapter = mAdapter
        }
    }

    private fun onMovieCardClicked(id : Int) {
            GlobalObject.setMovieId(id)
            val intent = Intent(requireActivity(),MoviesDetailsActivity::class.java)
            startActivity(intent)
    }

}