package com.ore.oremovieapp.ui.movies


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ore.oremovieapp.R
import com.ore.oremovieapp.data.FavoriteAdapter
import com.ore.oremovieapp.data.Movie
import com.ore.oremovieapp.data.MovieDatabase
import com.ore.oremovieapp.databinding.FragmentFavoritesBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass.
 */
class FragmentFavorites : Fragment() {

    private var viewModel: MovieViewModel? = null
    private lateinit var binding: FragmentFavoritesBinding
    private var database = MovieDatabase
    private lateinit var movie: Movie

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_favorites, container, false
            )
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProvider(this).get((MovieViewModel::class.java))

        viewModel?.favoriteMovies?.observeForever { favoriteMovies ->
            val adapter = context?.let {
                FavoriteAdapter(favoriteMovies, it, { movie ->
                    Toast.makeText(context, movie.title, Toast.LENGTH_LONG).show()
                }, { favoriteMovie, view ->
                    favoriteMovie.isFavorite = false
                    movie = Movie(
                        favoriteMovie.id,
                        favoriteMovie.title,
                        favoriteMovie.release_date,
                        favoriteMovie.overview,
                        favoriteMovie.backdrop_path,
                        favoriteMovie.vote_average,
                        favoriteMovie.poster_path,
                        favoriteMovie.isFavorite
                    )
                    Toast.makeText(context, "${movie.isFavorite}", Toast.LENGTH_LONG).show()
                    GlobalScope.launch(Dispatchers.IO) {
                        database.getInstance(context!!)?.favoriteDao()?.delete(favoriteMovie)
                        database.getInstance(context!!)?.movieDao()?.updateMovie(movie)
                    }
                })
            }
            binding.recyclerView.adapter = adapter
//            adapter.setMovies(allMovies)
        }
//        val manager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
//        binding.recyclerView.layoutManager = manager

        val manager = LinearLayoutManager(context)
        binding.recyclerView.layoutManager = manager
    }
}
