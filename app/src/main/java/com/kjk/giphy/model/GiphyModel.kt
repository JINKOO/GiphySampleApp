package com.kjk.giphy.model

import android.util.Log
import com.kjk.giphy.network.API_KEY_GIPHY
import com.kjk.giphy.network.BASE_URL_GIPHY
import com.kjk.giphy.network.GiphyService
import com.kjk.giphy.network.model.ResponseTrendingGifs
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GiphyModel : GiphyDataSender {

    private var giphyItemList = arrayListOf<GiphyItemEntity>()

    private val giphyService: GiphyService
    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL_GIPHY)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        giphyService = retrofit.create(GiphyService::class.java)
    }

    override fun getGiphyItemList(): ArrayList<GiphyItemEntity> {
        return this.giphyItemList
    }

    fun setGiphyItemList() {
        // NetWork 통신 전 임시 테스트 데이터 넣기
//        repeat(MAX_ITEM_SIZE) {
//            giphyItemList.add(GiphyItemEntity(it.toString(), false))
//        }

        giphyService.getTrendingList(API_KEY_GIPHY)
            .enqueue(object: Callback<ResponseTrendingGifs> {
                override fun onResponse(
                    call: Call<ResponseTrendingGifs>,
                    response: Response<ResponseTrendingGifs>
                ) {
                    if (response.isSuccessful.not()) {
                        Log.d(TAG, "isSuccessfulNot: ${response.errorBody()}")
                        return
                    }

                    response.body()?.let {
                        Log.d(TAG, "onResponse: ${it.data.size}, ${it.pagination.total_count}")
                    }
                }

                override fun onFailure(call: Call<ResponseTrendingGifs>, t: Throwable) {
                    Log.d(TAG, "onFailure: ")
                }
            })
    }

    companion object {
        private const val TAG = "GiphyModel"
        private const val MAX_ITEM_SIZE = 100
    }
}