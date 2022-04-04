package com.example.brewdogbeer.di

import com.example.brewdogbeer.api.ApiRestRepository
import com.example.brewdogbeer.api.BrewdogBeerApi
import com.example.brewdogbeer.api.Repository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {
    private val URL_API = "https://api.punkapi.com/"
    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
            .baseUrl(URL_API)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    internal fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient
            .Builder()
            .build()

    @Provides
    @Singleton
    fun provideGson(): Gson =
        GsonBuilder().create()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): BrewdogBeerApi {
        return retrofit.create(BrewdogBeerApi::class.java)
    }

    @Provides
    @Singleton
    internal fun provideRepository(api: BrewdogBeerApi): Repository {
        return ApiRestRepository(api)
    }
}