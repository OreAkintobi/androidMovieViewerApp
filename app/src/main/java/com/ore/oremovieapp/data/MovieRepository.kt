package com.ore.oremovieapp.data

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import com.google.gson.GsonBuilder
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException

class MovieRepository(application: Application) {

    private var movieDatabase: MovieDatabase = MovieDatabase.getInstance(application)!!
    private val repositoryDao = movieDatabase.movieDao()

    private lateinit var list: ArrayList<Movie>
    private lateinit var roomMovieAdapter: MovieAdapter

    fun fetchAllMovies() {
        val url =
            "https://api.themoviedb.org/3/movie/popular?api_key=dc11806c0f50e774518de6304a107103&language=en-US&page=2"
        val request = Request.Builder().url(url).build()
        val client = OkHttpClient()
        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: okhttp3.Response) {
                val body = response.body?.string()
                println(body)
//                Log.d("MOVIE", body!!)

                val gson = GsonBuilder().create()
                val results = gson.fromJson(body, Result::class.java)
                for (movie in results.results) {
                    repositoryDao.insert(movie)
                    Log.d("MOVIE", movie.toString())
                }
            }

            override fun onFailure(call: Call, e: IOException) {
                println("Failed to Execute ${e.printStackTrace()}")
            }
        })
    }

    fun getAllMovies(): LiveData<List<Movie>> = repositoryDao.getAllMovies()

//    val allMovies: LiveData<List<Movie>> = movieDao.getAlphabetizedMovies()

    fun insert(movie: Movie) {
        repositoryDao.insert(movie)
    }


}