package com.kjk.giphy.data.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface GiphyDatabaseDAO {

    /**
     *  network로 부터 fetch해 insert 했던
     *  data를 fetch.
     */
    @Query("SELECT * FROM giphy_database")
    fun getAllGiphies(): LiveData<List<GiphyDatabaseEntity>>


    /**
     *  network로 부터 fetch한 data를 room database에 insert하는 함수.
     *  기존에 network로 부터 fetch한 data가 존재하는 경우,
     *  새로이 network로 부터 fetch한 최신 data를 덮어 씌운다.
     *  onConflictStrategy.REPLACE를 사용하는 이유.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(giphyList: List<GiphyDatabaseEntity>)
}