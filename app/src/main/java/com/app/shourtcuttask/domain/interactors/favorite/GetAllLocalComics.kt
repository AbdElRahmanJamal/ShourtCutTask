package com.app.shourtcuttask.domain.interactors.favorite

import com.app.shourtcuttask.domain.interactors.BaseUseCase
import com.app.shourtcuttask.domain.repository.favorite.IFavoriteComicRepository
import com.app.shourtcuttask.domain.utils.ResponseState
import com.app.shourtcuttask.framework.datasource.local.entity.ComicDbTable
import javax.inject.Inject

class GetAllLocalComics @Inject constructor(
    private val repository: IFavoriteComicRepository
) : BaseUseCase<ResponseState<List<ComicDbTable>>, Unit> {

    override suspend fun invoke(params: Unit): ResponseState<List<ComicDbTable>> {
        return repository.getLocalComics()
    }
}