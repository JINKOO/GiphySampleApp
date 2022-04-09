package com.kjk.giphy.database

import androidx.room.Database

@Database(entities = [Giphy::class], version = 1)
abstract class GiphyDatabase {
    abstract fun getGiphyDAO(): GiphyDAO
}