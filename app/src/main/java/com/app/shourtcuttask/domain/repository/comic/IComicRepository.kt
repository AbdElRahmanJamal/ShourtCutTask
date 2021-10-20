package com.app.shourtcuttask.domain.repository.comic

import com.app.shourtcuttask.framework.datasource.remote.dto.ComicDto
import com.app.shourtcuttask.domain.utils.ResponseState
import com.app.shourtcuttask.framework.datasource.local.entity.ComicDbTable

interface IComicRepository {

    suspend fun getComicByComicID(comicID: Int): ResponseState<ComicDto>

    suspend fun saveComicByComicID(comic: ComicDbTable)
}
