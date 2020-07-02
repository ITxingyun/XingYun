package com.xingyun.android.model.source.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Page(@PrimaryKey val articleType: String, val nextPage: Int?)