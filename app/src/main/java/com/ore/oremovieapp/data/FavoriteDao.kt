package com.ore.oremovieapp.data

import androidx.lifecycle.LiveData
import androidx.room.*


/** DAO -> Repository -> ViewModel **/

@Dao
interface FavoriteDao {

    @Query("SELECT * FROM favorites_table")
    fun getAllMovies(): LiveData<List<FavoriteMovie>>

    @Query("SELECT * FROM favorites_table")
    fun getFavoriteMovies(): LiveData<List<FavoriteMovie>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(movie: FavoriteMovie)

    @Insert
    fun insertAll(vararg movie: FavoriteMovie)

    @Query("DELETE FROM favorites_table")
    suspend fun deleteAll()

    @Delete
    suspend fun delete(favoriteMovie: FavoriteMovie)

    @Query("SELECT * from favorites_table ORDER BY title ASC")
    fun getAlphabetizedMovies(): LiveData<List<FavoriteMovie>>

}
