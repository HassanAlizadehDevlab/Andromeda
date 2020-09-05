package com.android.data.repository.datasource.comic

import com.android.data.entity.model.local.ComicEntity
import io.reactivex.Completable
import io.reactivex.Flowable

/**
 * Created by hassanalizadeh on 29,August,2020
 */
interface ComicDataSource {
    // First is totalCount
    fun comics(): Flowable<Pair<Int, List<ComicEntity>>>
    fun loadComics(characterId: Int): Completable
    fun loadMoreComics(): Completable
}