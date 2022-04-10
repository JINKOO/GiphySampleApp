package com.kjk.giphy.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Giphy(
    @PrimaryKey val id: String,
    val title: String,
    val thumbnailUrl: String,
    var isFavorite: Boolean = false,
    val offset: Int
)
