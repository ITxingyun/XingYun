package com.xingyun.android.model.source.db

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.xingyun.android.model.bean.Article

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticles(articles: List<Article>)

    @Query("SELECT * FROM articles WHERE articleType = :articleType")
    fun getArticlesByType(articleType: String): PagingSource<Int, Article>

    @Query("DELETE FROM articles WHERE articleType = :articleType")
    suspend fun removeByArticleType(articleType: String)

}