package com.app.shourtcuttask.domain.interactors.comic

import com.app.shourtcuttask.domain.interactors.BaseUseCase
import com.app.shourtcuttask.domain.repository.comic.IComicRepository
import com.app.shourtcuttask.framework.datasource.local.entity.ComicDbTable
import javax.inject.Inject

class SaveComicUseCase @Inject constructor(
    private val repository: IComicRepository
) : BaseUseCase<Unit, ComicDbTable> {

    override suspend fun invoke(params: ComicDbTable) {
        repository.saveComicByComicID(params)
    }
}