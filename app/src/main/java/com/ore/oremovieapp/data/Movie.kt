package com.ore.oremovieapp.data

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "movie_table")
@Parcelize
data class Movie(
    @PrimaryKey @ColumnInfo(name = "id") val id: Long,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "release_date") val release_date: String,
    @ColumnInfo(name = "overview") val overview: String,
    @ColumnInfo(name = "backdrop_path") val backdrop_path: String,
    @ColumnInfo(name = "vote_average") val vote_average: String,
    @ColumnInfo(name = "poster_path") val poster_path: String,
    @ColumnInfo(name = "is_favorite") var isFavorite: Boolean = false

) : Parcelable

@Parcelize
data class Result(
    val page: Long,
    val totalResults: Long,
    val totalPages: Long,
    val results: List<Movie>
) : Parcelable