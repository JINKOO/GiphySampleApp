package com.kjk.giphy.data.network

import com.kjk.giphy.data.network.model.ResponseTrendingGifs
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.*

const val API_KEY_GIPHY = "uKr6E4b7NV9Z7YJifSb6ePeVQXL6aoAC"
const val BASE_URL_GIPHY = "https://api.giphy.com"

/**
 * retrofit initailize
 */
private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL_GIPHY)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

/**
 * giphy service
 * API에서 모든 giphy list를 가져온다.
 */
interface GiphyApiService {
    @GET("/v1/gifs/trending")
    suspend fun getAllGiphies(
        @Query("api_key") apiKey: String = API_KEY_GIPHY,
        @Query("limit") limit: Int = 25,
        @Query("offset") offset: Int = 0,
        @Query("random_id") randomId: String = ContentRating.G.toString().lowercase(Locale.ENGLISH)
        //@Query("bundle") bundle: String
    ): ResponseTrendingGifs
}

/**
 *  giphy Api Service
 *  Singleton으로 구현하기 위해, object 키워드를 사용한다.
 *  object 키워드는 singleton의 구현할 때 사용한다.
 */
object GiphyApi {
    val retrofitService: GiphyApiService by lazy {
        retrofit.create(GiphyApiService::class.java)
    }
}

/**
 * content rating을 하는
 * enum class이다.
 */
enum class ContentRating {
    G,
    PG,
    PG_13,
    R
}
