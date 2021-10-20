package com.app.shourtcuttask.framework.di

import com.app.shourtcuttask.data.dataSource.comic.IComicLocalDataSource
import com.app.shourtcuttask.data.dataSource.comic.IComicRemoteDataSource
import com.app.shourtcuttask.data.repository.comic.ComicRepository
import com.app.shourtcuttask.domain.interactors.comic.GetComicByIdUseCase
import com.app.shourtcuttask.domain.interactors.comic.SaveComicUseCase
import com.app.shourtcuttask.domain.repository.comic.IComicRepository
import com.app.shourtcuttask.framework.datasource.local.ComicDao
import com.app.shourtcuttask.framework.datasource.local.ComicLocalDataSource
import com.app.shourtcuttask.framework.datasource.remote.ComicApi
import com.app.shourtcuttask.framework.datasource.remote.ComicRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher

@Module
@InstallIn(ViewModelComponent::class)
object ComicViewModelModule {

    @Provides
    @ViewModelScoped
    fun providesGetComicByIdUseCase(
        @DIQualifier.ComicRepositoryQualifier
        repository: IComicRepository
    ): GetComicByIdUseCase = GetComicByIdUseCase(repository)

    @Provides
    @ViewModelScoped
    fun providesSaveComicUseCase(
        @DIQualifier.ComicRepositoryQualifier
        repository: IComicRepository
    ): SaveComicUseCase = SaveComicUseCase(repository)

    @Provides
    @ViewModelScoped
    @DIQualifier.ComicRepositoryQualifier
    fun providesComicRepository(
        @DIQualifier.ComicRemoteDatasourceQualifier
        remoteDataSource: IComicRemoteDataSource,
        @DIQualifier.ComicLocalDatasourceQualifier
        localDataSource: IComicLocalDataSource,
    ): IComicRepository = ComicRepository(remoteDataSource, localDataSource)

    @Provides
    @ViewModelScoped
    @DIQualifier.ComicRemoteDatasourceQualifier
    fun providesComicRemoteDataSource(
        api: ComicApi,
        dispatcher: CoroutineDispatcher
    ): IComicRemoteDataSource = ComicRemoteDataSource(api, dispatcher)

    @Provides
    @ViewModelScoped
    @DIQualifier.ComicLocalDatasourceQualifier
    fun providesComicLocalDataSource(
        comicDao: ComicDao
    ): IComicLocalDataSource = ComicLocalDataSource(comicDao)
}