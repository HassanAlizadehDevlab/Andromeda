package com.android.data.repository.datasource.character

import com.android.data.entity.model.local.CharacterEntity
import io.reactivex.Completable
import io.reactivex.Flowable

/**
 * Created by hassanalizadeh on 29,August,2020
 */
interface CharacterDataSource {
    // First is totalCount
    fun characters(): Flowable<Pair<Int, List<CharacterEntity>>>
    fun loadCharacters(): Completable
    fun loadMoreCharacters(): Completable
}