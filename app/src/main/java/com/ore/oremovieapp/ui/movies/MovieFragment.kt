package com.ore.oremovieapp.ui.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
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


        binding.openAppButton.setOnClickListener {
            //            view.findNavController().navigate(R.id.action_fragmentOpenApp_to_fragmentAddContact)
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

//    private fun initializeUi() {
//        val factory = InjectorUtils.provideMovieViewModelFactory()
//        viewModel = ViewModelProvider(this, factory).get(MovieViewModel::class.java)
//
//        viewModel.getMovies().observe(this, Observer { movies ->
////            val stringBuilder = StringBuilder()
////            movies.forEach { movie ->
////                stringBuilder.append("$movie\n\n")
////            }
//        })
//    }

//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//        viewModel = ViewModelProvider(this).get(MovieViewModel::class.java)
//        // TODO: Use the ViewModel
//    }

}
