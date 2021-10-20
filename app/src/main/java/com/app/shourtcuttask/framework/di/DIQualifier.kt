package com.app.shourtcuttask.framework.di

import javax.inject.Qualifier

object DIQualifier {

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class ComicRepositoryQualifier

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class ComicFavoriteRepositoryQualifier

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class ComicRemoteDatasourceQualifier

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class ComicLocalDatasourceQualifier

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class ComicFavoriteLocalDatasourceQualifier
}