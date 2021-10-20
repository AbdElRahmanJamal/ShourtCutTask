package com.app.shourtcuttask.data.dataSource.comic

import com.app.shourtcuttask.framework.datasource.remote.dto.ComicDto
import com.app.shourtcuttask.domain.utils.ResponseState

interface IComicRemoteDataSource {

    suspend fun getComicByComicID(comicID: Int): ResponseState<ComicDto>
}