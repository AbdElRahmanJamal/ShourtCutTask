package com.app.shourtcuttask.framework.datasource.local

import com.app.shourtcuttask.data.dataSource.comic.IComicLocalDataSource
import com.app.shourtcuttask.framework.datasource.local.entity.ComicDbTable
import javax.inject.Inject

class ComicLocalDataSource @Inject constructor(
    private val comicDao: ComicDao
) : IComicLocalDataSource {

    override suspend fun saveComicByComicID(comic: ComicDbTable) {
        comicDao.insertComic(comic)
    }
}