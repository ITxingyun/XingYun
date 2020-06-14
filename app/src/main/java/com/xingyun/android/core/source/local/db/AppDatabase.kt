package com.xingyun.android.core.source.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.xingyun.android.core.model.Article
import com.xingyun.android.core.model.ProjectCategory
import com.xingyun.android.core.model.Tag
import com.xingyun.android.core.model.TagsConverters

@Database(
        version = 1,
        entities = [
            Article::class
        ]
)
@TypeConverters(TagsConverters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

}