package com.rasyidin.mygithubapp.core.di

import com.rasyidin.mygithubapp.BuildConfig
import com.rasyidin.mygithubapp.BuildConfig.GITHUB_TOKEN
import com.rasyidin.mygithubapp.core.utils.Constant.AUTHORIZATION
import com.rasyidin.mygithubapp.home.data.source.remote.network.HomeService
import com.rasyidin.mygithubapp.profile.data.source.remote.network.ProfileService
import com.rasyidin.mygithubapp.search.data.source.remote.network.SearchService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun providesHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor { chain ->
            chain.proceed(
                chain.request()
                    .newBuilder()
                    .addHeader(AUTHORIZATION,   "token $GITHUB_TOKEN")
                    .build()
            )
        }
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .build()

    @Provides
    @Singleton
    fun providesHomeService(): HomeService = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(providesHttpClient())
        .build()
        .create(HomeService::class.java)

    @Provides
    @Singleton
    fun providesProfileService(): ProfileService = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(providesHttpClient())
        .build()
        .create(ProfileService::class.java)

    @Provides
    @Singleton
    fun providesSearchService(): SearchService = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(providesHttpClient())
        .build()
        .create(SearchService::class.java)

}