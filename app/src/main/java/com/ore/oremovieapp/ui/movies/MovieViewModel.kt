package com.ore.oremovieapp.ui.movies

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.ore.oremovieapp.data.Movie
import com.ore.oremovieapp.data.MovieRepository

class MovieViewModel(application: Application) : AndroidViewModel(application) {

    private var movieRepository = MovieRepository(application)

    var allMovies = movieRepository.getAllMovies()
    var favoriteMovies = movieRepository.getFavoriteMovies()

    fun insert(movie: Movie) = movieRepository.insert(movie)

    fun loadMoviesIntoDB() {
        movieRepository.fetchAllMovies()
    }
}
