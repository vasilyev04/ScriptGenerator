package com.example.scriptgenerator.di.modules

import com.example.scriptgenerator.data.remote.GeminiApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    companion object{
        @Provides
        @Singleton
        fun provideGeminiApi() = GeminiApi
    }
}