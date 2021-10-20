package com.app.shourtcuttask.domain.interactors

interface BaseUseCase<T, in Params> {

    suspend operator fun invoke(params: Params): T
}