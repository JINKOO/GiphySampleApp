package com.kjk.giphy.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.kjk.giphy.network.model.Data

@Dao
interface GiphyDAO {

    @Query("SELECT * FROM Giphy")
    fun loadAllGiphy(): LiveData<List<Giphy>>

    @Insert
    fun addGiphy()

    @Delete
    fun deleteGiphy()
}