package com.kjk.giphy.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kjk.giphy.data.domain.GiphyProperty

@Entity(tableName = "giphy_database")
data class GiphyDatabaseEntity(
    @PrimaryKey
    val id: String,
    val title: String,
    val thumbnailUrl: String,
    var isFavorite: Boolean = false,
    val offset: Int
)

fun List<GiphyDatabaseEntity>.asDomainModel(): List<GiphyProperty> {
    return map {
        GiphyProperty(
            id = it.id,
            title = it.title,
            thumbnailUrl = it.thumbnailUrl,
            isFavorite = it.isFavorite,
            offset = it.offset
        )
    }
}
