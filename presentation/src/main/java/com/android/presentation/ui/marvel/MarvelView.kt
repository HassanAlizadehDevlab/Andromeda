package com.android.presentation.ui.marvel

import com.android.domain.entity.CharacterObject
import com.android.domain.entity.ComicObject

/**
 * Created by hassanalizadeh on 06,September,2020
 */
interface MarvelView {
    fun setCharacters(items: List<CharacterObject>)
    fun setComics(items: List<ComicObject>)
    fun loading(status: Boolean)
    fun comicsLoading(status: Boolean)
}