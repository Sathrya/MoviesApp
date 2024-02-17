package com.example.moviesapp.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.moviesapp.data.MovieItem
import com.example.moviesapp.databinding.FragmentPopularMoviesBinding
import com.example.moviesapp.ui.MoviesDetailsActivity
import com.example.moviesapp.ui.adapters.MoviesAdapter
import com.example.moviesapp.ui.viewmodel.PopularMoviesViewModel
import com.example.moviesapp.utils.GlobalObject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class PopularMoviesFragment : Fragment() {

    private lateinit var binding: FragmentPopularMoviesBinding
    private val thisViewModel : PopularMoviesViewModel by viewModel()
    private lateinit var mAdapter : MoviesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPopularMoviesBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        thisViewModel.getAllPopularMovies()
        setUpObservers()
    }

    private fun setUpObservers() {
        thisViewModel.latestMovieList.observe(viewLifecycleOwner){
            initViews(it!!)
        }
    }

    private fun initViews(list: List<MovieItem>) {
        mAdapter = MoviesAdapter(this::onMovieCardClicked)
        Log.d("Success","$list.toString()")
        if (list.isNotEmpty()){
            binding.mainLoader2.visibility = View.GONE
        }
        else {
            binding.mainLoader2.visibility = View.VISIBLE
        }

        mAdapter.setItems(list)
        binding.popularMoviesContainer.apply {
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