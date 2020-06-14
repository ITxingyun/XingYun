package com.xingyun.android.di.module

import android.content.Context
import androidx.room.Room
import com.xingyun.android.core.http.XYRetrofitService
import com.xingyun.android.core.source.local.LocalDataSource
import com.xingyun.android.core.source.local.db.AppDatabase
import com.xingyun.android.core.source.remote.RemoteDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object AppModule {

    @JvmStatic
    @Singleton
    @Provides
    fun provideRemoteDataSource(
            retrofitService: XYRetrofitService
    ): RemoteDataSource {
        return RemoteDataSource(retrofitService)
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideLocalDataSource(
            appDatabase: AppDatabase
    ): LocalDataSource {
        return LocalDataSource(appDatabase.userDao())
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