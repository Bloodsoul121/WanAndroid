package com.blood.wanandroid.net

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class HttpModule {

    companion object {
        const val DEFAULT_TIME_OUT = 15
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().connectTimeout(
            DEFAULT_TIME_OUT.toLong(), TimeUnit.SECONDS
        ).readTimeout(
            DEFAULT_TIME_OUT.toLong(), TimeUnit.SECONDS
        ).writeTimeout(
            DEFAULT_TIME_OUT.toLong(), TimeUnit.SECONDS
        ).addInterceptor(LogInterceptor()).build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl("https://www.wanandroid.com/").client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create()).build()
    }

    @Provides
    fun provideNetApi(retrofit: Retrofit): HttpApi {
        return retrofit.create(HttpApi::class.java)
    }

}