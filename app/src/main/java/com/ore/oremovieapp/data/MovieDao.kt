package com.ore.oremovieapp.data

import androidx.lifecycle.LiveData
import androidx.room.*


/** DAO -> Repository -> ViewModel **/

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie_table")
    fun getAllMovies(): LiveData<List<Movie>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(movie: Movie)

    @Insert
    fun insertAll(vararg movie: Movie)

    @Query("DELETE FROM movie_table")
    suspend fun deleteAll()

    @Query("SELECT * from movie_table ORDER BY title ASC")
    fun getAlphabetizedMovies(): LiveData<List<Movie>>

    @Update
    fun updateMovie(movie: Movie)
}
