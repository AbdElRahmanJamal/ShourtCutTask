package com.app.shourtcuttask.data.dataSource.favoriteComic

import com.app.shourtcuttask.domain.utils.ResponseState
import com.app.shourtcuttask.framework.datasource.local.entity.ComicDbTable

interface IFavoriteComicLocalDataSource {

    suspend fun getLocalComics(): ResponseState<List<ComicDbTable>>
}