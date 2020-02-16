package com.ore.oremovieapp.data

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getColor
import androidx.recyclerview.widget.RecyclerView
import com.ore.oremovieapp.R
import com.ore.oremovieapp.databinding.FavoriteViewBinding
import com.squareup.picasso.Picasso

/** THIS CLASS PROVIDES/INFLATES A UNIT TEMPLATE OF WHAT EACH CONTACT DISPLAYED IN A RECYCLERVIEW LIST WOULD LOOK LIKE **/
class FavoriteAdapter(
    private var movies: List<FavoriteMovie>,
    private var context: Context,
    private val clickListener: (FavoriteMovie) -> Unit,
    private val clickListener2: (FavoriteMovie, View) -> Unit
) :
    RecyclerView.Adapter<FavoriteAdapter.MovieViewHolder>() {
    private var results = emptyList<FavoriteMovie>()
    private lateinit var binding: FavoriteViewBinding


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieViewHolder {
        val inflater = LayoutInflater.from(context)
        binding = FavoriteViewBinding.inflate(inflater, parent, false)

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

    class MovieViewHolder(private val binding: FavoriteViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            data: FavoriteMovie,
            clickListener: (FavoriteMovie) -> Unit,
            clickListener2: (FavoriteMovie, View) -> Unit
        ) {
            binding.favoriteMovie = data
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