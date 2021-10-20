package com.app.shourtcuttask.framework.datasource.remote

import com.app.shourtcuttask.framework.datasource.remote.dto.ComicDto
import retrofit2.http.GET
import retrofit2.http.Path

interface ComicApi {

    @GET("/{comicId}/info.0.json")
    suspend fun getComicByID(@Path("comicId") comicId: Int): ComicDto

}