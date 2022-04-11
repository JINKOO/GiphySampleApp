package com.kjk.giphy.data

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.kjk.giphy.data.database.Giphy
import com.kjk.giphy.data.database.GiphyDatabase
import com.kjk.giphy.data.network.API_KEY_GIPHY
import com.kjk.giphy.data.network.BASE_URL_GIPHY
import com.kjk.giphy.data.network.GiphyService
import com.kjk.giphy.data.network.model.ResponseTrendingGifs
import com.kjk.giphy.data.network.model.toGiphyList
import com.kjk.giphy.trending.TrendingFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.IllegalStateException
import java.util.concurrent.Executors

class GiphyRepository private constructor(context: Context)  {

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL_GIPHY)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val giphyService = retrofit.create(GiphyService::class.java)

    private val database: GiphyDatabase = Room.databaseBuilder(
        context.applicationContext,
        GiphyDatabase::class.java,
        DATABASE_NAME
    ).build()

    private val giphyDao = database.getGiphyDAO()
    private val executor = Executors.newSingleThreadExecutor()

    fun loadAllGiphy(): LiveData<List<Giphy>> = giphyDao.loadAllGiphy()
    fun loadFavoriteGiphy(): LiveData<List<Giphy>> = giphyDao.loadFavoriteGiphyList()

    fun updateGiphy(giphy: Giphy) {
        executor.execute{
            Log.d(TAG, "updateGiphy: ${giphy}")
            giphyDao.updateGiphy(giphy)
        }
    }

    fun deleteGiphy(giphy: Giphy) {
        executor.execute{
            giphyDao.deleteGiphy(giphy)
        }
    }

    fun getRemoteGiphyList(){
        giphyService.getTrendingList().enqueue(
            object : Callback<ResponseTrendingGifs> {
                override fun onResponse(
                    call: Call<ResponseTrendingGifs>,
                    response: Response<ResponseTrendingGifs>
                ) {
                    if (response.isSuccessful.not()) {
                        Log.d(TAG, "onResponse: notSuccesful ")
                        return
                    }

                    response.body()?.let {
                        executor.execute {
                            Log.d(TAG, "onResponse: ${it.data.size}")
                            giphyDao.insertAllGiphy(it.toGiphyList())
                        }
                    }
                }
                override fun onFailure(call: Call<ResponseTrendingGifs>, t: Throwable) {
                    Log.d(TAG, "onFailure: ${t}")
                }
            }
        )
    }

    companion object {
        private const val TAG = "GiphyRepository"
        private const val DATABASE_NAME = "giphy-database"
        private var INSTANCE: GiphyRepository? = null

        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = GiphyRepository(context)
            }
        }

        fun get(): GiphyRepository {
            return INSTANCE ?: throw IllegalStateException(
                "GiphyRepository must be initialized"
            )
        }
    }
}