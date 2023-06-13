package com.devcode.tourifyapp.data.local.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.devcode.tourifyapp.data.local.entity.DestinationEntity

@Dao
interface DestinationDao {
    @Query("SELECT * FROM destination ORDER BY name ASC")
    fun getFavorite(): LiveData<List<DestinationEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavorite(user: DestinationEntity)

    @Delete
    suspend fun deleteFavorite(note: DestinationEntity)

    @Query("SELECT * FROM destination WHERE id = :id")
    suspend fun getById(id: String): DestinationEntity
}