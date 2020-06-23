package com.xingyun.android.di.module

import android.content.Context
import androidx.room.Room
import com.xingyun.android.model.http.api.WebService
import com.xingyun.android.model.source.local.LocalArticleDataSource
import com.xingyun.android.model.source.local.db.AppDatabase
import com.xingyun.android.model.source.remote.RemoteArticleDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object AppModule {

    @JvmStatic
    @Singleton
    @Provides
    fun provideRemoteArticleDataSource(
            retrofitService: WebService
    ): RemoteArticleDataSource {
        return RemoteArticleDataSource(retrofitService)
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideLocalArticleDataSource(
            appDatabase: AppDatabase
    ): LocalArticleDataSource {
        return LocalArticleDataSource(appDatabase.userDao())
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideDataBase(context: Context): AppDatabase {
        return Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "XY.db"
        ).build()
    }

}