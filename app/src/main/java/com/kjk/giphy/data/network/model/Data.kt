package com.kjk.giphy.data.network.model

import com.giphy.sdk.core.models.Images

data class Data(
    val id: String,
    val images: Images,
    val title: String
)
