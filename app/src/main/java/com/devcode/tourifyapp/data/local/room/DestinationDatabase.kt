package com.devcode.tourifyapp.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.devcode.tourifyapp.data.local.entity.DestinationEntity

@Database(entities = [DestinationEntity::class], version = 1, exportSchema = false)
abstract class DestinationDatabase : RoomDatabase() {
    abstract fun destinationDao(): DestinationDao

    companion object {
        @Volatile
        private var instance: DestinationDatabase? = null
        fun getInstance(context: Context): DestinationDatabase =
            instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(
                    context.applicationContext,
                    DestinationDatabase::class.java, "Destination.db"
                ).build()
            }
    }
}