package com.kjk.giphy.model

import android.util.Log
import com.kjk.giphy.network.API_KEY_GIPHY
import com.kjk.giphy.network.BASE_URL_GIPHY
import com.kjk.giphy.network.GiphyService
import com.kjk.giphy.network.model.Data
import com.kjk.giphy.network.model.ResponseTrendingGifs
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GiphyModel : GiphyDataSender {

    private var giphyDataList = arrayListOf<Data>()
    private var giphyFavoriteList = arrayListOf<Data>()

    override fun getGiphyDataList(): List<Data> {
        return this.giphyDataList
    }

    fun setGiphyDataList(giphyDataList: List<Data>) {
        this.giphyDataList = giphyDataList as ArrayList<Data>
    }

    fun setFavoriteGiphyList() {

    }

    fun getFavoriteGiphyList(): List<Data> {
        return this.giphyFavoriteList
    }

    companion object {
        private const val TAG = "GiphyModel"
    }
}