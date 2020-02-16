package com.ore.oremovieapp


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.ore.oremovieapp.data.MovieAdapter
import com.ore.oremovieapp.data.MovieRepository
import com.ore.oremovieapp.databinding.FragmentAllMoviesBinding
import com.ore.oremovieapp.ui.movies.MovieViewModel

/** SHOWS RECYCLERVIEW OF ALL CONTACTS STORED **/
class FragmentAllMovies : Fragment() {

    private var viewModel: MovieViewModel? = null
    private lateinit var binding: FragmentAllMoviesBinding


    private lateinit var movieRepository: MovieRepository

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_all_movies, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProvider(this).get((MovieViewModel::class.java))

        viewModel!!.loadMoviesIntoDB()

        viewModel!!.allMovies.observeForever { allMovies ->
            val adapter = MovieAdapter(allMovies, this.context!!) {
                Log.e("MOVIES", allMovies.toString())
                view!!.findNavController().navigate(
                    FragmentAllMoviesDirections.actionFragmentAllMoviesToFragmentViewSingleMovie(
                        it
                    )
                )
            }
            binding.recyclerView.adapter = adapter
//            adapter.setMovies(allMovies)
        }
        val manager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        binding.recyclerView.layoutManager = manager
    }
}
