package com.app.shourtcuttask.framework.datasource.remote

import com.app.shourtcuttask.data.dataSource.comic.IComicRemoteDataSource
import com.app.shourtcuttask.framework.datasource.remote.dto.ComicDto
import com.app.shourtcuttask.domain.utils.BundleKeys
import com.app.shourtcuttask.domain.utils.ResponseState
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class ComicRemoteDataSource @Inject constructor(
    private val api: ComicApi,
    dispatcher: CoroutineDispatcher
) : BaseRemoteDataSource<ComicDto>(dispatcher = dispatcher), IComicRemoteDataSource {


    override suspend fun startRemoteApiCall(apiParameter: HashMap<String, Any>?): ComicDto {
        var coinID = 1
        apiParameter?.let {
            if (it.getValue(BundleKeys.COMIC_ID_HASH_MAP_KEY) is Int)
                coinID = it.getValue(BundleKeys.COMIC_ID_HASH_MAP_KEY) as Int
        }
        return api.getComicByID(coinID)
    }

    override suspend fun getComicByComicID(comicID: Int): ResponseState<ComicDto> {
        val apiKeys = HashMap<String, Any>()
        apiKeys[BundleKeys.COMIC_ID_HASH_MAP_KEY] = comicID
        return getResponseState(apiKeys)
    }

    override fun onSuccess(apiResponse: ComicDto): ResponseState<ComicDto> {
        return ResponseState.Data(apiResponse)
    }
}