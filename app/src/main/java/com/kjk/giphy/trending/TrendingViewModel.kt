package com.kjk.giphy.trending

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.kjk.giphy.data.GiphyRepository
import com.kjk.giphy.data.domain.GiphyProperty
import kotlinx.coroutines.launch
import timber.log.Timber

class TrendingViewModel(
    application: Application
) : AndroidViewModel(application) {

    /**
     * repository instance
     */
    private val giphyRepository = GiphyRepository(application)

    /**
     * repository로 부터 fetch한 data
     * network로 부터 바로 fetch한 data가 아니라,
     * netowrk로 부터 fetch한 data를 database에 insert후,
     * database에서 fetch한 data.
     */
    var giphyProperties: LiveData<List<GiphyProperty>> = giphyRepository.giphyProperties


    /**
     *  api network status LiveData
     */
    private val _apiStatus = MutableLiveData<GiphyApiStatus>()
    val apiStatus: LiveData<GiphyApiStatus>
        get() = _apiStatus


    init {
        refreshGiphyListFromRepository()
    }

    /**
     *  network, database의 data sync를 맞추기 위한
     *  database refresh 전략을 사용해야한다.
     *  'Repository에 data 요청한 놈이, 최신화의 책임을 가진다.'
     */
    private fun refreshGiphyListFromRepository() {
        viewModelScope.launch {
            _apiStatus.value = GiphyApiStatus.LOADING
            try {
                giphyRepository.refresh()
                _apiStatus.value = GiphyApiStatus.DONE
                Timber.d("${giphyProperties.value!!.size}")
            } catch(e: Exception) {
                Timber.d("${e.message}")
                _apiStatus.value = GiphyApiStatus.ERROR
            }
        }
    }

    companion object {
        private const val TAG = "TrendingViewModel"
    }
}

enum class GiphyApiStatus {
    LOADING,
    ERROR,
    DONE
}