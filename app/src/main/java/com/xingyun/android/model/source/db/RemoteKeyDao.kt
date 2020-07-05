package com.xingyun.android.model.source.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RemoteKeyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(remoteKey: List<RemoteKey>)

    @Query("SELECT * FROM remote_key WHERE id = :articleId")
    suspend fun remoteKeyByArticle(articleId: String): RemoteKey

    @Query("DELETE FROM remote_key WHERE articleType = :articleType")
    suspend fun removeByArticleType(articleType: String)
}