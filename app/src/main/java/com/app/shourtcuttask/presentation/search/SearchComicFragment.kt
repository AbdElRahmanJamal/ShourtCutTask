package com.app.shourtcuttask.presentation.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.observe
import com.app.shourtcuttask.databinding.SearchComicLayoutBinding
import com.app.shourtcuttask.domain.utils.UiState
import com.app.shourtcuttask.framework.ImageDownloader
import com.app.shourtcuttask.framework.datasource.local.entity.ComicDbTable
import com.app.shourtcuttask.framework.datasource.remote.dto.ComicDto
import com.app.shourtcuttask.presentation.explanation.ExplanationTray
import com.app.shourtcuttask.presentation.explanation.ExplanationTrayUIModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SearchComicFragment : Fragment() {

    @Inject
    lateinit var imageDownloader: ImageDownloader

    private val comicViewModel: SearchComicViewModel by viewModels()
    private lateinit var searchComicLayoutBinding: SearchComicLayoutBinding
    private var comicDbTable: ComicDbTable? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        searchComicLayoutBinding = SearchComicLayoutBinding.inflate(inflater, container, false)
        return searchComicLayoutBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchComicLayoutBinding.searchForComic.setOnClickListener {
            lifecycleScope.launchWhenCreated {
                if (searchComicLayoutBinding.comicId.text.isNullOrEmpty().not()) {
                    comicViewModel.getComic(
                        searchComicLayoutBinding.comicId.text.toString().toInt()
                    )
                    comicViewModel.comicState.observe(viewLifecycleOwner) {
                        handleComicState(it)
                    }
                }
            }
        }
        searchComicLayoutBinding.addToFavoriteComic.setOnClickListener {
            lifecycleScope.launchWhenCreated {
                comicDbTable?.let {
                    comicViewModel.saveComicByID(it)
                }
            }
        }
    }


    private fun handleComicState(state: UiState<ComicDto>) {
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
        searchComicLayoutBinding.loadingView.visibility = View.VISIBLE
        searchComicLayoutBinding.comicImage.visibility = View.GONE
        searchComicLayoutBinding.comicImage.setImageBitmap(null)
        setBtnIsClickable(searchComicLayoutBinding.addToFavoriteComic, false)
    }

    private fun handleSuccessState(value: ComicDto) {
        searchComicLayoutBinding.comicImage.apply {
            imageDownloader.downloadImage(
                value.img, {
                    visibility = View.VISIBLE
                    searchComicLayoutBinding.loadingView.visibility = View.GONE
                    setImageBitmap(it)
                    setBtnIsClickable(searchComicLayoutBinding.addToFavoriteComic, true)
                    comicDbTable = ComicDbTable(value.img, it, value.title, value.transcript)
                    setOnClickListener {
                        ExplanationTray(ExplanationTrayUIModel(value.title, value.transcript))
                            .show(parentFragmentManager, "ExplanationTray")
                    }
                }, {
                    visibility = View.VISIBLE
                    searchComicLayoutBinding.loadingView.visibility = View.GONE
                    setBtnIsClickable(searchComicLayoutBinding.addToFavoriteComic, true)
                })
        }
    }

    private fun handleErrorState() {
        searchComicLayoutBinding.loadingView.visibility = View.GONE
        searchComicLayoutBinding.comicImage.visibility = View.VISIBLE
        setBtnIsClickable(searchComicLayoutBinding.addToFavoriteComic, true)
    }

    private fun setBtnIsClickable(btn: Button, b: Boolean) {
        btn.isClickable = b
        btn.isEnabled = b
    }

}