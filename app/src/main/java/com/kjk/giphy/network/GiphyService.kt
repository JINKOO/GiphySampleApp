package com.kjk.giphy.network

import com.kjk.giphy.network.model.ResponseTrendingGifs
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.*

const val API_KEY_GIPHY = "uKr6E4b7NV9Z7YJifSb6ePeVQXL6aoAC"
const val BASE_URL_GIPHY = "https://api.giphy.com/"

interface GiphyService {

    @GET("v1/gifs/trending")
    fun getTrendingList(
        @Query("api_key") apiKey: String = API_KEY_GIPHY,
        @Query("limit") limit: Int = 25,
        @Query("offset") offset: Int = 0,
        @Query("random_id") randomId: String = ContentRating.G.toString().lowercase(Locale.ENGLISH)
        //@Query("bundle") bundle: String
    ): Call<ResponseTrendingGifs>
}

enum class ContentRating {
    G,
    PG,
    PG_13,
    R
}
