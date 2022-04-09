package com.kjk.giphy.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kjk.giphy.network.model.Data

@Entity
class Giphy {
    @PrimaryKey var id: String = ""
    var title: String = ""
}