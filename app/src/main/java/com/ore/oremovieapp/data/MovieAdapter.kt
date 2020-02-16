package com.ore.oremovieapp.data

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getColor
import androidx.recyclerview.widget.RecyclerView
import com.ore.oremovieapp.R
import com.ore.oremovieapp.databinding.MovieViewBinding
import com.squareup.picasso.Picasso

/** THIS CLASS PROVIDES/INFLATES A UNIT TEMPLATE OF WHAT EACH CONTACT DISPLAYED IN A RECYCLERVIEW LIST WOULD LOOK LIKE **/
class MovieAdapter(
    private var movies: List<Movie>,
    private var context: Context,
    private val clickListener: (Movie) -> Unit,
    private val clickListener2: (Movie, View) -> Unit
) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    private var results = emptyList<Movie>()
    private lateinit var binding: MovieViewBinding


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieViewHolder {
        val inflater = LayoutInflater.from(context)
        binding = MovieViewBinding.inflate(inflater, parent, false)

        return MovieViewHolder(binding)
    }

    override fun getItemCount() = movies.size

//    fun setMovies(movies: List<Movie>){
//        this.movies = movies
//        notifyDataSetChanged()
//    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        val path = movie.poster_path
        holder.bind(movie, clickListener, clickListener2)
//        Glide.with(binding.root.context).asBitmap()
//            .load("https://image.tmdb.org/t/p/w500/7IiTTgloJzvGI1TAYymCfbfl3vT.jpg")
//            .centerCrop().fallback(
//                R.drawable.ic_movie
//            ).error(R.drawable.ic_movie).placeholder(R.drawable.ic_movie)
//            .into(binding.movieImage2)

        Picasso.get()
            .load("https://image.tmdb.org/t/p/w500${path}")
            .placeholder(
                R.drawable.ic_movie
            )
            .error(R.drawable.ic_movie).into(binding.movieImage2)
    }

    class MovieViewHolder(private val binding: MovieViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            data: Movie,
            clickListener: (Movie) -> Unit,
            clickListener2: (Movie, View) -> Unit
        ) {
            binding.movie = data
            if (data.isFavorite) {
                binding.movieFaveButton.setBackgroundColor(
                    getColor(
                        binding.movieFaveButton.context,
                        R.color.design_default_color_surface
                    )
                )
            }
            binding.root.setOnClickListener { clickListener(data) }
            binding.movieFaveButton.setOnClickListener {
                clickListener2(data, binding.root)
            }
        }
    }
}