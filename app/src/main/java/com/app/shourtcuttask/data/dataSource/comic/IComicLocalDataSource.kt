package com.app.shourtcuttask.data.dataSource.comic

import com.app.shourtcuttask.framework.datasource.local.entity.ComicDbTable

interface IComicLocalDataSource {

    suspend fun saveComicByComicID(comic: ComicDbTable)
}