package com.kjk.giphy.trending

import android.app.Application
import androidx.lifecycle.*
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
     *  network API 상태 관리하는 LiveData
     */
    private val _apiStatus = MutableLiveData<GiphyApiStatus>()
    val apiStatus: LiveData<GiphyApiStatus>
        get() = _apiStatus


    /**
     *  Favorite Fragment로 navigate 하는 event trigger
     */
    private val _navigateToFavorite = MutableLiveData<Boolean>()
    val navigateToFavorite: LiveData<Boolean>
        get() = _navigateToFavorite



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
                Timber.d("Success :: ${giphyProperties.value!!.size}")
            } catch(e: Exception) {
                Timber.d("Failure :: ${e.message}")
                _apiStatus.value = GiphyApiStatus.ERROR
            }
        }
    }

    /**
     *  Favorite Fragment로 이동 완료 설정하는 함수
     */
    fun onNavigateFavoriteDone() {
        _navigateToFavorite.value = false
    }

    fun onFavoriteButtonClicked() {
        _navigateToFavorite.value = true
    }

    companion object {
        private const val TAG = "TrendingViewModel"
    }
}

/**
 *  API 상태 관리 enum class
 */
enum class GiphyApiStatus {
    LOADING,
    ERROR,
    DONE
}


/**
 *  Favorite 버튼 상태 관리 class
 */
enum class FavoriteButtonStatus {
    CLICKED,
    NOT_CLICKED
}