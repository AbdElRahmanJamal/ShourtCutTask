package com.app.shourtcuttask.data.repository.favorite

import com.app.shourtcuttask.data.dataSource.favoriteComic.IFavoriteComicLocalDataSource
import com.app.shourtcuttask.domain.repository.favorite.IFavoriteComicRepository
import com.app.shourtcuttask.domain.utils.ResponseState
import com.app.shourtcuttask.framework.datasource.local.entity.ComicDbTable
import javax.inject.Inject

class FavoriteComicRepository @Inject constructor(
    private val localDataSource: IFavoriteComicLocalDataSource
) : IFavoriteComicRepository {

    override suspend fun getLocalComics(): ResponseState<List<ComicDbTable>> {
        return localDataSource.getLocalComics()
    }
}