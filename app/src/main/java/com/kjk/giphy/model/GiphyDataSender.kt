package com.kjk.giphy.model

import com.kjk.giphy.network.model.Data

interface GiphyDataSender {
    // Retrofit을 사용해 실제 data를 가져오는 경우
    fun getGiphyDataList(): List<Data>
}