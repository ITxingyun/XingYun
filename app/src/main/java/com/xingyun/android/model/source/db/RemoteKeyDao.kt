package com.xingyun.android.model.source.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RemoteKeyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrReplace(page: Page)

    @Query("SELECT * FROM page WHERE articleType = :articleType")
    suspend fun remoteKeyByQuery(articleType: String): Page

    @Query("DELETE FROM page WHERE articleType = :articleType")
    suspend fun deleteByQuery(articleType: String)
}