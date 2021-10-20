package com.app.shourtcuttask.domain.repository.favorite

import com.app.shourtcuttask.domain.utils.ResponseState
import com.app.shourtcuttask.framework.datasource.local.entity.ComicDbTable

interface IFavoriteComicRepository {

    suspend fun getLocalComics(): ResponseState<List<ComicDbTable>>
}