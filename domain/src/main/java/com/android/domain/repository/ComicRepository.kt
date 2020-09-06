package com.android.domain.repository

import com.android.domain.entity.ComicsObject
import io.reactivex.Completable
import io.reactivex.Flowable

/**
 * Created by hassanalizadeh on 29,August,2020
 */
interface ComicRepository {
    fun comics(): Flowable<ComicsObject>
    fun loadComics(characterId: Int): Completable
    fun loadMoreComics(): Completable
}