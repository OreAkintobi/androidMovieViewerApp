package com.ore.oremovieapp.ui.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ore.oremovieapp.R
import com.ore.oremovieapp.data.FavoriteMovie
import com.ore.oremovieapp.data.MovieAdapter
import com.ore.oremovieapp.data.MovieDatabase
import com.ore.oremovieapp.databinding.FragmentAllMoviesBinding
import kotlinx.android.synthetic.main.movie_view.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/** SHOWS RECYCLERVIEW OF ALL CONTACTS STORED **/
class FragmentAllMovies() : Fragment() {

    private var viewModel: MovieViewModel? = null
    private lateinit var binding: FragmentAllMoviesBinding
    private var database = MovieDatabase
    private lateinit var favoriteMovie: FavoriteMovie

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_all_movies, container, false
            )
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProvider(this).get((MovieViewModel::class.java))

        viewModel!!.loadMoviesIntoDB()

        viewModel?.allMovies?.observeForever { allMovies ->
            val adapter = context?.applicationContext?.let {
                MovieAdapter(allMovies, it, { movie ->
                    view!!.findNavController().navigate(
                        FragmentAllMoviesDirections.actionFragmentAllMoviesToFragmentViewSingleMovie(
                            movie
                        )
                    )
                    Toast.makeText(context, movie.title, Toast.LENGTH_LONG).show()

                }, { movie, view ->
                    Toast.makeText(context, "before${movie.isFavorite}", Toast.LENGTH_SHORT).show()
                    movie.isFavorite = true
                    Toast.makeText(context, "after ${movie.isFavorite}", Toast.LENGTH_SHORT).show()
                    favoriteMovie = FavoriteMovie(
                        movie.id,
                        movie.title,
                        movie.release_date,
                        movie.overview,
                        movie.backdrop_path,
                        movie.vote_average,
                        movie.poster_path,
                        movie.isFavorite
                    )
                    if (movie.isFavorite) {
//                        !movie.isFavorite
                        view.movieFaveButtonAll.setBackgroundColor(resources.getColor(R.color.sea_green))
                        view.movieFaveButtonAll.text = "Remove from Favorites"
                        GlobalScope.launch(Dispatchers.IO) {
                            database.getInstance(context!!)?.favoriteDao()?.insert(favoriteMovie)
                            database.getInstance(context!!)?.movieDao()?.updateMovie(movie)
                        }
                    } else {
//                        !movie.isFavorite
//                        view.movieFaveButtonAll.setBackgroundColor(resources.getColor(R.color.design_default_color_error))
                        view.movieFaveButtonAll.text = "Add to Favorites"
                        GlobalScope.launch(Dispatchers.IO) {
                            database.getInstance(context!!)?.favoriteDao()?.delete(favoriteMovie)
                            database.getInstance(context!!)?.movieDao()?.updateMovie(movie)
                        }
                    }
                })
            }
            binding.recyclerView.adapter = adapter
        }
        val manager = LinearLayoutManager(context)
        binding.recyclerView.layoutManager = manager

    }
}
