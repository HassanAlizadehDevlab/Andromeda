package com.android.presentation.ui.marvel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.android.domain.entity.CharacterObject
import com.android.domain.entity.ComicObject
import com.android.presentation.R
import com.android.presentation.common.extension.linearLayout
import com.android.presentation.common.utils.Utils
import com.android.presentation.common.view.BaseFragment
import com.android.presentation.ui.marvel.adapter.CharactersAdapter
import com.android.presentation.ui.marvel.adapter.ComicsAdapter
import kotlinx.android.synthetic.main.fragment_marvel.*
import javax.inject.Inject

/**
 * Created by hassanalizadeh on 06,September,2020
 */
class MarvelViewImpl : BaseFragment(), MarvelView {

    @Inject
    lateinit var presenter: MarvelPresenter
    private lateinit var charactersAdapter: CharactersAdapter
    private lateinit var comicsAdapter: ComicsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        charactersAdapter = CharactersAdapter { holder ->
            presenter.observeClicks(holder.observe())
        }
        comicsAdapter = ComicsAdapter { holder -> }
        presenter.loadMoreCharacters(charactersAdapter.getLoadMoreObservable())
        presenter.loadMoreComics(comicsAdapter.getLoadMoreObservable())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_marvel, container, false)

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)

        setupRecyclerView()
        setupAdapter()

        presenter.refresh()
    }

    override fun setCharacters(items: List<CharacterObject>) {
        charactersAdapter.addItems(items.toMutableList())
    }

    override fun setComics(items: List<ComicObject>) {
        comicsAdapter.addItems(items.toMutableList())
    }

    override fun loading(status: Boolean) {

    }

    override fun comicsLoading(status: Boolean) {

    }


    private fun setupRecyclerView() {
        recyclerViewCharacters?.linearLayout(
            context = activityContext,
            orientation = RecyclerView.HORIZONTAL,
            noTopSpacingForFirstItem = true,
            spacing = Utils.convertDpToPx(activityContext, 4f).toInt()
        )

        recyclerViewComics?.linearLayout(
            context = activityContext,
            spacing = Utils.convertDpToPx(activityContext, 4f).toInt()
        )
    }

    private fun setupAdapter() {
        recyclerViewCharacters?.adapter = charactersAdapter
        recyclerViewComics?.adapter = comicsAdapter
    }


    companion object {
        fun newInstance(): Fragment = MarvelViewImpl()
    }

}