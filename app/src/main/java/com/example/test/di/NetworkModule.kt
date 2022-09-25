package com.example.test.di

import com.example.test.Const
import com.example.test.retrofit.BlogDetailsApi
import com.example.test.retrofit.MainPageApi
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
internal class NetworkModule {

    @Provides
    @Singleton
    fun provideMainPageApi(retrofit: Retrofit): MainPageApi {
        return retrofit.create(MainPageApi::class.java)
    }

    @Provides
    @Singleton
    fun provideBlogDetailsApi(retrofit: Retrofit): BlogDetailsApi {
        return retrofit.create(BlogDetailsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(createRequestInterceptor())
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        val contentType = Const.ACCEPT.toMediaType()
        return Retrofit.Builder()
            .baseUrl(Const.BASE_URL)
            .addConverterFactory(Json.asConverterFactory(contentType))
            .client(client)
            .build()
    }

    private fun createRequestInterceptor(): Interceptor {
        return HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)
    }
}