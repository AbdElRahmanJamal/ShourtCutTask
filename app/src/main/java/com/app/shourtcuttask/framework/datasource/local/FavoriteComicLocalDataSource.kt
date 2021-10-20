package com.app.shourtcuttask.framework.datasource.local

import com.app.shourtcuttask.data.dataSource.favoriteComic.IFavoriteComicLocalDataSource
import com.app.shourtcuttask.domain.utils.Constant
import com.app.shourtcuttask.domain.utils.ResponseState
import com.app.shourtcuttask.framework.datasource.local.entity.ComicDbTable
import javax.inject.Inject

class FavoriteComicLocalDataSource @Inject constructor(
    private val comicDao: ComicDao
) : IFavoriteComicLocalDataSource {

    override suspend fun getLocalComics(): ResponseState<List<ComicDbTable>> {
        return try {
            val comic = comicDao.getComicByComicID()
            if (comic.isNullOrEmpty().not()) {
                ResponseState.Data(comic)
            } else {
                ResponseState.Error(Constant.EMPTY_RESPONSE_ERROR_MESSAGE)
            }
        } catch (ex: Exception) {
            ResponseState.Error(Constant.EMPTY_RESPONSE_ERROR_MESSAGE)
        }
    }
}