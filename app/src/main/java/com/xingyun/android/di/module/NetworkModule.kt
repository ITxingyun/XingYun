package com.xingyun.android.di.module

import com.xingyun.android.model.http.NetworkInterceptor
import com.xingyun.android.model.http.api.WebService
import com.xingyun.android.model.http.cookies.CookiesManager
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
                .readTimeout(READ_TIME_OUT, TimeUnit.MILLISECONDS)
                .connectTimeout(CONNECT_TIME_OUT, TimeUnit.MILLISECONDS)
                .writeTimeout(WRITE_TIME_OUT, TimeUnit.MILLISECONDS)
                .cookieJar(CookiesManager())
                .addInterceptor(httpLoggingInterceptor)
                .addInterceptor(NetworkInterceptor())
                .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
    }

    @Singleton
    @Provides
    fun provideQuestionsAndAnswersService(retrofit: Retrofit): WebService {
        return retrofit.create(WebService::class.java)
    }

    private companion object {
        const val READ_TIME_OUT = 10000L
        const val WRITE_TIME_OUT = 10000L
        const val CONNECT_TIME_OUT = 10000L
        const val BASE_URL = "https://wanandroid.com/"
    }
}