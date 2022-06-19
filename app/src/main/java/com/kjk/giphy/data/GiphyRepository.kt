package com.kjk.giphy.data

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.kjk.giphy.data.database.GiphyDatabase
import com.kjk.giphy.data.database.GiphyDatabaseEntity
import com.kjk.giphy.data.database.asDomainModel
import com.kjk.giphy.data.domain.GiphyProperty
import com.kjk.giphy.data.network.GiphyApi
import com.kjk.giphy.data.network.model.toDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

class GiphyRepository (application: Application)  {

    /**
     * database
     */
    private val databaseDao =
        GiphyDatabase.getInstance(application).databaseDao

    /**
     * retrofit
     */
    private val retrofitService = GiphyApi.retrofitService

    /**
     *  database로 부터 fetch한 data
     */
    private val giphyDatabaseEntities: LiveData<List<GiphyDatabaseEntity>> = databaseDao.getAllGiphies()

    /**
     *  UI Controller에서 사용하기 위해,
     *  Transformation을 사용해, Domain Object로 변환
     */
    val giphyProperties: LiveData<List<GiphyProperty>> = Transformations.map(giphyDatabaseEntities) {
        it.asDomainModel()
    }

    /**
     *  network로 부터 fetch한 data를
     *  room database에 insert한다.
     *  database refresh
     */
    suspend fun refresh() {
        withContext(Dispatchers.IO) {
            // 1. network로 부터 fetch
            val giphiesFromNetwork = retrofitService.getAllGiphies()
            Timber.d("${giphiesFromNetwork.data.size}")

            // 2. database에 insert
            databaseDao.insertAll(giphiesFromNetwork.toDatabaseModel())
        }
    }

    companion object {
        private const val TAG = "GiphyRepository"
    }
}