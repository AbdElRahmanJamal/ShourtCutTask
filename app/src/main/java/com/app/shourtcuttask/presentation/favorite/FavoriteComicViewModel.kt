package com.app.shourtcuttask.presentation.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.shourtcuttask.domain.interactors.favorite.GetAllLocalComics
import com.app.shourtcuttask.domain.utils.Constant
import com.app.shourtcuttask.domain.utils.ResponseState
import com.app.shourtcuttask.domain.utils.UiState
import com.app.shourtcuttask.framework.datasource.local.entity.ComicDbTable
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteComicViewModel @Inject constructor(
    private val getAllLocalComics: GetAllLocalComics,
    private val coroutineDispatcher: CoroutineDispatcher
) : ViewModel() {

    var comicIndex = 0
    private val _comicState = MutableLiveData<UiState<ComicDbTable>>()
    private var listOfLocalComics = mutableListOf<ComicDbTable>()
    var comicState: LiveData<UiState<ComicDbTable>> = _comicState

    suspend fun getFirstComic() {
        viewModelScope.launch(coroutineDispatcher) {
            _comicState.postValue(UiState.LoadingState)
            val comicState = getAllLocalComics(Unit)
            if (comicState is ResponseState.Data) {
                comicState.data?.let {
                    listOfLocalComics.addAll(it)
                    _comicState.postValue(UiState.DataState(it[0]))
                }
                    ?: _comicState.postValue(UiState.ErrorState(Constant.EMPTY_RESPONSE_ERROR_MESSAGE))
            } else {
                _comicState.postValue(UiState.ErrorState(Constant.GENERIC_ERROR_MESSAGE))
            }
        }
    }

    fun getNextComic() {
        comicIndex++
        if (comicIndex > listOfLocalComics.size - 1) comicIndex = 0

        if (listOfLocalComics.isNotEmpty() && comicIndex <= listOfLocalComics.size - 1) {
            _comicState.postValue(UiState.DataState(listOfLocalComics[comicIndex]))
        }
    }

    fun getPrevComic() {
        comicIndex--
        if (comicIndex < 0) comicIndex = listOfLocalComics.size - 1
        if (listOfLocalComics.isNotEmpty() && comicIndex >= 0) {
            _comicState.postValue(UiState.DataState(listOfLocalComics[comicIndex]))
        }
    }
}