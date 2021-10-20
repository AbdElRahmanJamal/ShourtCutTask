package com.app.shourtcuttask.data.repository.comic

import com.app.shourtcuttask.data.dataSource.comic.IComicLocalDataSource
import com.app.shourtcuttask.data.dataSource.comic.IComicRemoteDataSource
import com.app.shourtcuttask.domain.repository.comic.IComicRepository
import com.app.shourtcuttask.domain.utils.ResponseState
import com.app.shourtcuttask.framework.datasource.local.entity.ComicDbTable
import com.app.shourtcuttask.framework.datasource.remote.dto.ComicDto
import javax.inject.Inject

class ComicRepository @Inject constructor(
    private val remoteDataSource: IComicRemoteDataSource,
    private val localDataSource: IComicLocalDataSource,
) : IComicRepository {

    override suspend fun getComicByComicID(comicID: Int): ResponseState<ComicDto> {
        return remoteDataSource.getComicByComicID(comicID)
    }

    override suspend fun saveComicByComicID(comic: ComicDbTable) {
        localDataSource.saveComicByComicID(comic)
    }
}