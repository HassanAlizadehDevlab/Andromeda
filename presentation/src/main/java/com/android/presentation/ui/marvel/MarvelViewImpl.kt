package com.android.presentation.ui.marvel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.domain.entity.CharacterObject
import com.android.domain.entity.ComicObject
import com.android.presentation.R
import com.android.presentation.common.view.BaseFragment
import javax.inject.Inject

/**
 * Created by hassanalizadeh on 06,September,2020
 */
class MarvelViewImpl : BaseFragment(), MarvelView {

    @Inject
    lateinit var presenter: MarvelPresenter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_marvel, container, false)

    override fun setCharacters(items: List<CharacterObject>) {
        TODO("Not yet implemented")
    }

    override fun setComics(items: List<ComicObject>) {
        TODO("Not yet implemented")
    }

    override fun loading(status: Boolean) {
        TODO("Not yet implemented")
    }

    override fun comicsLoading(status: Boolean) {
        TODO("Not yet implemented")
    }

}