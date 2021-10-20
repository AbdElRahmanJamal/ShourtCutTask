package com.app.shourtcuttask.framework.di

import android.content.Context
import androidx.room.Room
import com.app.shourtcuttask.domain.utils.Constant
import com.app.shourtcuttask.framework.datasource.local.ComicDao
import com.app.shourtcuttask.framework.datasource.local.ComicDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ComicDBModule {

    @Provides
    @Singleton
    fun providesComicDao(comicDatabase: ComicDatabase): ComicDao =
        comicDatabase.comicDao

    @Provides
    @Singleton
    fun providesComicDatabase(@ApplicationContext context: Context): ComicDatabase {
        synchronized(this) {
            Room.databaseBuilder(
                context.applicationContext,
                ComicDatabase::class.java,
                Constant.DATA_BASE_NAME
            ).build().also {
                return it
            }
        }
    }
}



