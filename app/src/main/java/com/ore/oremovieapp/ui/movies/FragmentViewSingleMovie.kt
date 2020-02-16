package com.ore.oremovieapp.ui.movies


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.ore.oremovieapp.R
import com.ore.oremovieapp.databinding.FragmentViewSingleMovieBinding
import com.squareup.picasso.Picasso

class FragmentViewSingleMovie : Fragment() {
    private lateinit var binding: FragmentViewSingleMovieBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_view_single_movie, container, false
            )

        val args =
            FragmentViewSingleMovieArgs.fromBundle(
                arguments!!
            )
        val movie = args.movie
        binding.movie = movie
        val path = movie.poster_path


//        Glide.with(this).asBitmap()
//            .load("https://image.tmdb.org/t/p/w500/7IiTTgloJzvGI1TAYymCfbfl3vT.jpg")
//            .centerCrop().fallback(
//            R.drawable.ic_movie
//        ).error(R.drawable.ic_movie).placeholder(R.drawable.ic_movie)
//            .into(binding.movieImage)

        Picasso.get()
            .load("https://image.tmdb.org/t/p/w500${path}")
            .placeholder(
                R.drawable.ic_movie
            )
            .error(R.drawable.ic_movie).into(binding.movieImage)

        return binding.root
    }
}
