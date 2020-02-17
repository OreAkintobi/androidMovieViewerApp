package com.ore.oremovieapp.ui.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.ore.oremovieapp.R
import com.ore.oremovieapp.databinding.MovieFragmentBinding

class MovieFragment : Fragment() {

//    companion object {
//        fun newInstance() = MovieFragment()
//    }

    private lateinit var viewModel: MovieViewModel
    private lateinit var topAnim: Animation
    private lateinit var bottomAnim: Animation

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: MovieFragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.movie_fragment, container, false)

        Toast.makeText(
            context,
            R.string.toast_open_app,
            Toast.LENGTH_SHORT
        ).show()

//        initializeUi()


        binding.viewFavoriteMoviesButton.setOnClickListener {
            val action = MovieFragmentDirections.actionFragmentMainToFragmentFavorites(null)
            Navigation.findNavController(it).navigate(action)
//            view?.findNavController()?.navigate(R.id.action_fragmentMain_to_fragmentFavorites)
        }

        binding.viewAllMoviesButton.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_fragmentMain_to_fragmentAllMovies)
        }

        binding.killAppButton.setOnClickListener {
            //            exitApp()
        }

//        binding.logoutButton.setOnClickListener {
//            val prefs: SharedPreferences = getSharedPreferences("MY_PREF", Context.MODE_PRIVATE)
//            prefs.edit().clear().apply()
//            finish()
//        }

        return binding.root
    }
}
