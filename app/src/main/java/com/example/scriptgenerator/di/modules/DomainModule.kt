package com.example.scriptgenerator.di.modules

import com.example.scriptgenerator.data.repository.ScriptRepositoryImpl
import com.example.scriptgenerator.domain.repository.ScriptRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DomainModule {

    @Binds
    @Singleton
    fun bindScriptRepository(
        impl: ScriptRepositoryImpl
    ): ScriptRepository
}