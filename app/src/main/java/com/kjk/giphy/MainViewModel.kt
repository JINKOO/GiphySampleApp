package com.kjk.giphy

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.kjk.giphy.data.GiphyRepository
import com.kjk.giphy.data.database.Giphy
import com.kjk.giphy.data.network.model.Data

class MainViewModel : ViewModel() {

    private val giphyRepository = GiphyRepository.get()

    val giphyFavoriteLiveData = giphyRepository.loadFavoriteGiphy()
    var giphyLiveData: LiveData<List<Giphy>> = giphyRepository.loadAllGiphy()

    init {
        getInitialGiphyList()
    }

    fun updateGiphy(giphy: Giphy) {
        giphyRepository.updateGiphy(giphy)
    }

    fun deleteGiphy(giphy: Giphy) {
        giphyRepository.deleteGiphy(giphy)
    }

    fun getInitialGiphyList() {
        giphyRepository.getRemoteGiphyList()
    }

    companion object {
        private const val TAG = "MainViewModel"
    }
}