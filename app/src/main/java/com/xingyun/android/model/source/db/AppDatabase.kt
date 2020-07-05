package com.xingyun.android.model.source.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.xingyun.android.model.bean.Article
import com.xingyun.android.model.bean.TagsConverters

@Database(
        entities = [
            RemoteKey::class,
            Article::class
        ],
        version = 1,
        exportSchema = false
)
@TypeConverters(TagsConverters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun articleDao(): ArticleDao

    abstract fun remoteKeyDao(): RemoteKeyDao

}