package com.tabi.tmdbexplorer.di

import com.tabi.tmdbexplorer.BuildConfig.BASE_URL
import com.tabi.tmdbexplorer.data.remote.api.TmdbAPI
import com.tabi.tmdbexplorer.repository.ITmdbRepository
import com.tabi.tmdbexplorer.repository.TmdbRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideDefaultShoppingRepository(
        api: TmdbAPI
    ) = TmdbRepository(api) as ITmdbRepository

    @Singleton
    @Provides
    fun provideTmdbApi(): TmdbAPI {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(TmdbAPI::class.java)
    }
}