package com.kjk.giphy.network

import retrofit2.http.GET
import retrofit2.http.Query

interface GiphyService {

    @GET("v1/gifs/trending")
    fun getTrendingList(
        @Query("api_key") apiKey: String,
    )
}     