package com.xingyun.android.core.source.local.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.xingyun.android.core.model.Article

@Dao
interface UserDao {

    @Query("SELECT * FROM article WHERE superChapterId IN (:superChapterId)")
    fun loadArticleByChapterId(superChapterId: Int): LiveData<List<Article>>

    @Insert
    fun insertArticles(articles: List<Article>)


}