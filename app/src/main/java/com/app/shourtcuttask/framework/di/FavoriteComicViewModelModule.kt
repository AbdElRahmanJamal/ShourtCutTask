package com.app.shourtcuttask.framework.di

import com.app.shourtcuttask.data.dataSource.favoriteComic.IFavoriteComicLocalDataSource
import com.app.shourtcuttask.data.repository.favorite.FavoriteComicRepository
import com.app.shourtcuttask.domain.interactors.favorite.GetAllLocalComics
import com.app.shourtcuttask.domain.repository.favorite.IFavoriteComicRepository
import com.app.shourtcuttask.framework.datasource.local.ComicDao
import com.app.shourtcuttask.framework.datasource.local.FavoriteComicLocalDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object FavoriteComicViewModelModule {

    @Provides
    @ViewModelScoped
    fun providesGetAllLocalComics(
        @DIQualifier.ComicFavoriteRepositoryQualifier
        repository: IFavoriteComicRepository
    ): GetAllLocalComics = GetAllLocalComics(repository)

    @Provides
    @ViewModelScoped
    @DIQualifier.ComicFavoriteRepositoryQualifier
    fun providesFavoriteComicRepository(
        @DIQualifier.ComicFavoriteLocalDatasourceQualifier
        localDataSource: IFavoriteComicLocalDataSource
    ): IFavoriteComicRepository = FavoriteComicRepository(localDataSource)

    @Provides
    @ViewModelScoped
    @DIQualifier.ComicFavoriteLocalDatasourceQualifier
    fun providesFavoriteFavoriteComicLocalDataSource(
        comicDao: ComicDao
    ): IFavoriteComicLocalDataSource = FavoriteComicLocalDataSource(comicDao)
}