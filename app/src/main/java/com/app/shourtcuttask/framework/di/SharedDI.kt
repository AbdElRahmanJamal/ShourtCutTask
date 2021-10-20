package com.app.shourtcuttask.framework.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SharedDI {

    @Provides
    @Singleton
    fun providesCoroutineDispatcher(
    ): CoroutineDispatcher {
        return Dispatchers.IO
    }
}