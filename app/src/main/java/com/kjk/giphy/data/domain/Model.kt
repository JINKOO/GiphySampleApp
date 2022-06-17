package com.kjk.giphy.data.domain

/**
 * domain object
 * 실제 UI Controller에서 사용하는 data model class
 * network, database로 부터 fetch한 response data를
 * UI Controller에서 사용하기 위해서는 domain object로 변환해야한다.
 */
data class GiphyProperty(
    val id: String,
    val title: String,
    val thumbnailUrl: String,
    var isFavorite: Boolean = false,
    val offset: Int
)

