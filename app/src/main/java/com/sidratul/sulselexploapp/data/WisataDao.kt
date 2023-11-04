package com.sidratul.sulselexploapp.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface WisataDao {
    @Query("SELECT * FROM tour")
    fun getAllTour(): Flow<List<WisataEntity>>

    @Query("SELECT * FROM tour WHERE isFavorite = 1")
    fun getAllFavoriteTour(): Flow<List<WisataEntity>>

    @Query("SELECT * FROM tour WHERE id = :id")
    fun getTour(id: Int): Flow<WisataEntity>

    @Query("SELECT * FROM tour WHERE name LIKE '%' || :query || '%'")
    fun searchTour(query: String): Flow<List<WisataEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllTour(tourList: List<WisataEntity>)

    @Query("UPDATE tour SET isFavorite = :isFavorite WHERE id = :id")
    suspend fun updateFavoriteTour(id: Int, isFavorite: Boolean)
}