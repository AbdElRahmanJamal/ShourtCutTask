package com.app.shourtcuttask.domain.interactors.comic

import com.app.shourtcuttask.domain.interactors.BaseUseCase
import com.app.shourtcuttask.framework.datasource.remote.dto.ComicDto
import com.app.shourtcuttask.domain.repository.comic.IComicRepository
import com.app.shourtcuttask.domain.utils.ResponseState
import javax.inject.Inject

class GetComicByIdUseCase @Inject constructor(
    private val repository: IComicRepository
) : BaseUseCase<ResponseState<ComicDto>, Int> {

    override suspend fun invoke(params: Int): ResponseState<ComicDto> {
        return repository.getComicByComicID(params)
    }
}