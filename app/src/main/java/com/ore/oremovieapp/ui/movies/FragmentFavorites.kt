package com.ore.oremovieapp.ui.movies


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.ore.oremovieapp.R
import com.ore.oremovieapp.data.FavoriteAdapter
import com.ore.oremovieapp.databinding.FragmentFavoritesBinding

/**
 * A simple [Fragment] subclass.
 */
class FragmentFavorites : Fragment() {

    private var viewModel: MovieViewModel? = null
    private lateinit var binding: FragmentFavoritesBinding

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

        viewModel!!.favoriteMovies.observeForever { favoriteMovies ->
            val adapter = FavoriteAdapter(favoriteMovies, this.context!!, { movie ->
                Toast.makeText(context, movie.title, Toast.LENGTH_LONG).show()
            }, { movie, view ->
                movie.isFavorite = !movie.isFavorite
                Toast.makeText(context, "${movie.isFavorite}", Toast.LENGTH_LONG).show()
            })
            binding.recyclerView.adapter = adapter
//            adapter.setMovies(allMovies)
        }
        val manager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        binding.recyclerView.layoutManager = manager
    }
}
