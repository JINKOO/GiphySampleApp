package com.kjk.giphy.data.network.model

import com.kjk.giphy.data.database.Giphy

data class ResponseTrendingGifs(
    val data: List<Data>,
    val meta: Meta,
    val pagination: Pagination
)

fun ResponseTrendingGifs.toGiphyList(): List<Giphy> {
    return this.data.map {
        Giphy(
            id = it.id,
            title = it.title,
            thumbnailUrl = it.images.fixedWidth?.gifUrl ?: "",
            offset = 0
        )
    }
}