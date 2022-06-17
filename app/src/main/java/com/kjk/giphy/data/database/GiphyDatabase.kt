package com.kjk.giphy.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [GiphyDatabaseEntity::class], version = 1)
abstract class GiphyDatabase : RoomDatabase() {

    abstract val databaseDao: GiphyDatabaseDAO

    companion object {
        @Volatile
        private var INSTANCE: GiphyDatabase? = null
        fun getInstance(context: Context): GiphyDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context,
                        GiphyDatabase::class.java,
                        "giphy_database"
                    ).build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}