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
    fun insertArticles(articles: List<Article>)

    @Query("DELETE FROM article WHERE link = :articleType")
    fun deleteByQuery(articleType: String)

    @Query("SELECT * FROM article WHERE link = :articleType")
    fun articlePagingSource(articleType: String): PagingSource<Int, Article>

}