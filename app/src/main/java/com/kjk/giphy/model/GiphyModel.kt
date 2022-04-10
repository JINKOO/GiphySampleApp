package com.kjk.giphy.model

import android.util.Log
import androidx.lifecycle.LiveData
import com.kjk.giphy.data.GiphyRepository
import com.kjk.giphy.data.database.Giphy
import com.kjk.giphy.data.network.model.Data

class GiphyModel : GiphyDataSender {

    private val repository = GiphyRepository.get()

    private var giphyDataList = arrayListOf<Data>()

    private var giphyFavoriteList = arrayListOf<Giphy>()

    override fun getGiphyDataList(): List<Data> {
        return this.giphyDataList
    }


    fun getFavoriteGiphyList(): LiveData<List<Giphy>> {
//        Log.d(TAG, "getFavoriteGiphyList: ${repository.loadAllGiphy().value!!.size}")
        return repository.loadAllGiphy()
    }

    companion object {
        private const val TAG = "GiphyModel"
    }
}