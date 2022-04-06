package com.kjk.giphy.network.model

data class ResponseTrendingGifs(
    val data: List<Data>,
    val meta: Meta,
    val pagination: Pagination
)