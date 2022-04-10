package com.kjk.giphy.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Giphy::class], version = 1)
abstract class GiphyDatabase : RoomDatabase() {
    abstract fun getGiphyDAO(): GiphyDAO
}