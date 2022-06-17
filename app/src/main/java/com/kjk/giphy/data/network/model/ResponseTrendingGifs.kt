package com.kjk.giphy.data.network.model

import com.kjk.giphy.data.database.GiphyDatabaseEntity
import com.kjk.giphy.data.domain.GiphyProperty

/**
 *  network response object.
 */
data class ResponseTrendingGifs(
    val data: List<Data>,
    val meta: Meta,
    val pagination: Pagination
)


/**
 *  network로 부터 받은 response data를
 *  room database에 저장하기 위해,
 *  network model object를 database model object로 변환.
 */
fun ResponseTrendingGifs.toDatabaseModel(): List<GiphyDatabaseEntity> {
    return data.map {
        GiphyDatabaseEntity(
            id = it.id,
            title = it.title,
            thumbnailUrl = it.images.fixedWidth?.gifUrl ?: "",
            offset = 0
        )
    }
}


/**
 * network response object를
 * domain object로 변환하는 함수
 */
fun ResponseTrendingGifs.toDomainModel(): List<GiphyProperty> {
    return data.map {
        GiphyProperty(
            id = it.id,
            title = it.title,
            thumbnailUrl = it.images.fixedWidth?.gifUrl ?: "",
            offset = 0
        )
    }
}