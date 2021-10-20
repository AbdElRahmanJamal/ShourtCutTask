package com.app.shourtcuttask.presentation.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.shourtcuttask.domain.interactors.comic.GetComicByIdUseCase
import com.app.shourtcuttask.domain.interactors.comic.SaveComicUseCase
import com.app.shourtcuttask.domain.utils.Constant
import com.app.shourtcuttask.domain.utils.ResponseState
import com.app.shourtcuttask.domain.utils.UiState
import com.app.shourtcuttask.framework.datasource.local.entity.ComicDbTable
import com.app.shourtcuttask.framework.datasource.remote.dto.ComicDto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchComicViewModel @Inject constructor(
    private val getComicByID: GetComicByIdUseCase,
    private val saveComicUseCase: SaveComicUseCase,
    private val coroutineDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _comicState = MutableLiveData<UiState<ComicDto>>()
    var comicState: LiveData<UiState<ComicDto>> = _comicState


    suspend fun saveComicByID(comic: ComicDbTable) {
        saveComicUseCase(comic)
    }

    suspend fun getComic(comicID: Int) {
        viewModelScope.launch(coroutineDispatcher) {
            _comicState.postValue(UiState.LoadingState)
            val comicState = getComicByID(comicID)
            if (comicState is ResponseState.Data) {
                comicState.data?.let {
                    _comicState.postValue(UiState.DataState(it))
                }
                    ?: _comicState.postValue(UiState.ErrorState(Constant.EMPTY_RESPONSE_ERROR_MESSAGE))
            } else {
                _comicState.postValue(UiState.ErrorState(Constant.GENERIC_ERROR_MESSAGE))
            }
        }
    }
}