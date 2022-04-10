package com.kjk.giphy.data.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface GiphyDAO {
    @Query("SELECT * FROM Giphy")
    fun loadAllGiphy(): LiveData<List<Giphy>>

    @Query("SELECT * FROM Giphy WHERE isFavorite = 1")
    fun loadFavoriteGiphyList(): LiveData<List<Giphy>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllGiphy(giphyList: List<Giphy>)

    @Update
    fun updateGiphy(giphy: Giphy)

    @Delete
    fun deleteGiphy(giphy: Giphy)
}