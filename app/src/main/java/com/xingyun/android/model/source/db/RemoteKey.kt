package com.xingyun.android.model.source.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remote_key")
data class RemoteKey(
        @PrimaryKey val id: String,
        val prevPage: Int?,
        val nextPage: Int?,
        val articleType: String
)