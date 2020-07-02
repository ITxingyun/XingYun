package com.xingyun.android.model.source.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.xingyun.android.model.bean.Article
import com.xingyun.android.model.bean.TagsConverters

@Database(
        version = 1,
        entities = [
            Page::class,
            Article::class
        ]
)
@TypeConverters(TagsConverters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun articleDao(): ArticleDao

    abstract fun remoteKeyDao(): RemoteKeyDao

}