package com.app.shourtcuttask.presentation.comic

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.observe
import com.app.shourtcuttask.databinding.ComicLayoutBinding
import com.app.shourtcuttask.domain.utils.UiState
import com.app.shourtcuttask.framework.ImageDownloader
import com.app.shourtcuttask.framework.datasource.local.entity.ComicDbTable
import com.app.shourtcuttask.framework.datasource.remote.dto.ComicDto
import com.app.shourtcuttask.presentation.explanation.ExplanationTray
import com.app.shourtcuttask.presentation.explanation.ExplanationTrayUIModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ComicFragment : Fragment() {
    @Inject
    lateinit var imageDownloader: ImageDownloader

    private val comicViewModel: ComicViewModel by viewModels()
    private lateinit var comicLayoutBinding: ComicLayoutBinding
    private var comicDbTable: ComicDbTable? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        comicLayoutBinding = ComicLayoutBinding.inflate(inflater, container, false)
        return comicLayoutBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launchWhenCreated {
            comicViewModel.getNextComicByID()
            observeOnComicState()
        }
        comicLayoutBinding.addToFavoriteComic.setOnClickListener {
            lifecycleScope.launchWhenCreated {
                comicDbTable?.let {
                    comicViewModel.saveComicByID(it)
                }
            }
        }

        comicLayoutBinding.nextComic.setOnClickListener {
            lifecycleScope.launchWhenCreated {
                comicViewModel.getNextComicByID()
                handleComicState(comicViewModel.comicState.value!!)
            }
        }
        comicLayoutBinding.previousComic.setOnClickListener {
            lifecycleScope.launchWhenCreated {
                comicViewModel.getPrevComicByID()
                observeOnComicState()
            }
        }
    }

    private fun observeOnComicState() {
        comicViewModel.comicState.observe(viewLifecycleOwner) {
            handleComicState(it)
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
        comicLayoutBinding.loadingView.visibility = View.VISIBLE
        comicLayoutBinding.comicImage.visibility = View.GONE
        comicLayoutBinding.comicImage.setImageBitmap(null)
        setBtnIsClickable(comicLayoutBinding.nextComic, false)
        setBtnIsClickable(comicLayoutBinding.previousComic, false)
        setBtnIsClickable(comicLayoutBinding.addToFavoriteComic, false)
    }

    private fun handleSuccessState(value: ComicDto) {
        comicLayoutBinding.comicImage.apply {
            imageDownloader.downloadImage(
                value.img, {
                    visibility = View.VISIBLE
                    comicLayoutBinding.loadingView.visibility = View.GONE
                    setImageBitmap(it)
                    enableAllButtons()
                    comicDbTable = ComicDbTable(value.img, it, value.title, value.transcript)
                    setOnClickListener {
                        ExplanationTray(ExplanationTrayUIModel(value.title, value.transcript))
                            .show(parentFragmentManager, "ExplanationTray")
                    }
                }, {
                    visibility = View.VISIBLE
                    comicLayoutBinding.loadingView.visibility = View.GONE
                    enableAllButtons()
                })
        }
    }

    private fun handleErrorState() {
        comicLayoutBinding.loadingView.visibility = View.GONE
        comicLayoutBinding.comicImage.visibility = View.VISIBLE
        enableAllButtons()
    }

    private fun enableAllButtons() {
        setBtnIsClickable(comicLayoutBinding.nextComic, true)
        setBtnIsClickable(comicLayoutBinding.previousComic, true)
        setBtnIsClickable(comicLayoutBinding.addToFavoriteComic, true)
    }

    private fun setBtnIsClickable(btn: Button, b: Boolean) {
        btn.isClickable = b
        btn.isEnabled = b
    }

}