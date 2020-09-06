package com.android.data.repository

import com.android.data.entity.mapper.mapComics
import com.android.data.repository.datasource.comic.ComicDataSource
import com.android.domain.entity.ComicsObject
import com.android.domain.repository.ComicRepository
import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject

/**
 * Created by hassanalizadeh on 05,September,2020
 */
class ComicRepositoryImpl @Inject constructor(
    private val dataSource: ComicDataSource
) : ComicRepository {

    override fun comics(): Flowable<ComicsObject> {
        return dataSource.comics().map {
            return@map ComicsObject(it.first, it.second.mapComics())
        }
    }

    override fun loadComics(characterId: Int): Completable {
        return dataSource.loadComics(characterId)
    }

    override fun loadMoreComics(): Completable {
        return dataSource.loadMoreComics()
    }

}