package com.xingyun.android.di.module

import android.content.Context
import androidx.room.Room
import com.xingyun.android.model.http.api.WebService
import com.xingyun.android.model.source.local.LocalArticleDataSource
import com.xingyun.android.model.source.local.LocalSearchDataSource
import com.xingyun.android.model.source.local.LocalUserProfileDataSource
import com.xingyun.android.model.source.local.db.AppDatabase
import com.xingyun.android.model.source.remote.RemoteArticleDataSource
import com.xingyun.android.model.source.remote.RemoteSearchDataSource
import com.xingyun.android.model.source.remote.RemoteUserProfileDataSource
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
    fun provideRemoteSearchDataSource(
            retrofitService: WebService
    ): RemoteSearchDataSource {
        return RemoteSearchDataSource(retrofitService)
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideLocalSearchDataSource(
            appDatabase: AppDatabase
    ): LocalSearchDataSource {
        return LocalSearchDataSource(appDatabase.userDao())
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideRemoteUserProfileDataSource(
            retrofitService: WebService
    ): RemoteUserProfileDataSource {
        return RemoteUserProfileDataSource(retrofitService)
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideLocalUserProfileDataSource(
            appDatabase: AppDatabase
    ): LocalUserProfileDataSource {
        return LocalUserProfileDataSource(appDatabase.userDao())
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