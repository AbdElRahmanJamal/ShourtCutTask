package com.app.shourtcuttask.presentation.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.app.shourtcuttask.databinding.FavoriteComicBinding
import com.app.shourtcuttask.domain.utils.UiState
import com.app.shourtcuttask.framework.datasource.local.entity.ComicDbTable
import com.app.shourtcuttask.presentation.explanation.ExplanationTray
import com.app.shourtcuttask.presentation.explanation.ExplanationTrayUIModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteComicFragment : Fragment() {

    private val favoriteComicViewModel: FavoriteComicViewModel by viewModels()
    private lateinit var favoriteComicBinding: FavoriteComicBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        favoriteComicBinding = FavoriteComicBinding.inflate(inflater, container, false)
        return favoriteComicBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launchWhenCreated {
            favoriteComicViewModel.getFirstComic()
            observeOnComicState()
        }
        favoriteComicBinding.nextComic.setOnClickListener {
            favoriteComicViewModel.getNextComic()
            handleComicState(favoriteComicViewModel.comicState.value!!)
        }
        favoriteComicBinding.previousComic.setOnClickListener {
            favoriteComicViewModel.getPrevComic()
            handleComicState(favoriteComicViewModel.comicState.value!!)
        }
    }

    private fun observeOnComicState() {
        favoriteComicViewModel.comicState.observe(viewLifecycleOwner) {
            handleComicState(it)
        }
    }

    private fun handleComicState(state: UiState<ComicDbTable>) {
        when (state) {
            is UiState.LoadingState -> {
                handleLoadingState()
            }
            is UiState.DataState -> {
                handleSuccessState(state.value)
            }
            is UiState.ErrorState -> {
                handleErrorState()
            }
        }
    }

    private fun handleLoadingState() {
        favoriteComicBinding.loadingView.visibility = View.VISIBLE
        favoriteComicBinding.comicImage.visibility = View.GONE
        favoriteComicBinding.comicImage.setImageBitmap(null)
        setBtnIsClickable(favoriteComicBinding.nextComic, false)
        setBtnIsClickable(favoriteComicBinding.previousComic, false)
    }

    private fun handleSuccessState(value: ComicDbTable) {
        favoriteComicBinding.loadingView.visibility = View.GONE
        favoriteComicBinding.comicImage.visibility = View.VISIBLE
        favoriteComicBinding.comicImage.setImageBitmap(value.img)
        favoriteComicBinding.comicImage.setOnClickListener {
            ExplanationTray(ExplanationTrayUIModel(value.title, value.description))
                .show(parentFragmentManager, "ExplanationTray")
        }
        setBtnIsClickable(favoriteComicBinding.nextComic, true)
        setBtnIsClickable(favoriteComicBinding.previousComic, true)
    }

    private fun handleErrorState() {
        favoriteComicBinding.loadingView.visibility = View.GONE
        favoriteComicBinding.comicImage.visibility = View.VISIBLE
        setBtnIsClickable(favoriteComicBinding.nextComic, true)
        setBtnIsClickable(favoriteComicBinding.previousComic, true)
    }

    private fun setBtnIsClickable(btn: Button, b: Boolean) {
        btn.isClickable = b
        btn.isEnabled = b
    }
}