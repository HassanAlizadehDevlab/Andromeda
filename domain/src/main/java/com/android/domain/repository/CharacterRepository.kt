package com.android.domain.repository

import com.android.domain.entity.CharactersObject
import io.reactivex.Completable
import io.reactivex.Flowable

/**
 * Created by hassanalizadeh on 29,August,2020
 */
interface CharacterRepository {
    fun characters(): Flowable<CharactersObject>
    fun loadCharacters(): Completable
    fun loadMoreCharacters(): Completable
}