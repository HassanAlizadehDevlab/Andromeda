package com.android.data.repository.datasource.comic

import androidx.annotation.VisibleForTesting
import com.android.data.entity.dao.ComicDao
import com.android.data.entity.mapper.mapComics
import com.android.data.entity.model.local.ComicEntity
import com.android.data.extension.onError
import com.android.data.network.DataServiceComic
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by hassanalizadeh on 29,August,2020
 */
open class SmartComicDataSource @Inject constructor(
    private val service: DataServiceComic,
    private val comicDao: ComicDao
) : ComicDataSource {

    @VisibleForTesting
    @Volatile
    var _page: Int = 1

    @VisibleForTesting
    @Volatile
    var _totalCount: Int = 0

    @VisibleForTesting
    @Volatile
    var _characterId: Int = 0


    override fun comics(): Flowable<Pair<Int, List<ComicEntity>>> {
        return comicDao.selectAll()
            .map {
                return@map if (_totalCount == 0)
                    it.size to it
                else
                    _totalCount to it
            }
            .onError()
    }

    override fun loadComics(characterId: Int): Completable {
        return resetPage()
            .andThen(setCharacterId(characterId))
            .andThen(getQueryParams())
            .flatMap { service.comics(_characterId, it) }
            .flatMap { setTotalCount(it.data.total).toSingle { it } }
            .flatMap { clearComics().toSingle { it } }
            .flatMapCompletable { insertComics(it.data.comics.mapComics()) }
            .andThen(increasePage())
            .onError()
    }

    override fun loadMoreComics(): Completable {
        return getQueryParams()
            .flatMap { service.comics(_characterId, it) }
            .flatMapCompletable { insertComics(it.data.comics.mapComics()) }
            .andThen(increasePage())
            .onError()
    }


    private fun insertComics(comics: List<ComicEntity>?): Completable {
        if (comics.isNullOrEmpty()) return Completable.complete()
        return Completable.fromAction { comicDao.insert(comics) }
            .onError()
    }

    private fun clearComics(): Completable {
        return Completable.fromAction { comicDao.deleteAll() }
    }

    private fun setTotalCount(totalCount: Int): Completable {
        return Completable.fromAction { this._totalCount = totalCount }
    }

    private fun setCharacterId(characterId: Int): Completable {
        return Completable.fromAction { this._characterId = characterId }
    }

    private fun getQueryParams(): Single<MutableMap<String, String>> {
        return Single.just(mutableMapOf<String, String>())
            .map {
                it["offset"] = _page.toString()
                return@map it
            }.onError()
    }

    private fun resetPage(): Completable {
        return Completable.fromAction {
            _page = 1
        }
    }

    private fun increasePage(): Completable {
        return Completable.fromAction {
            _page += 1
        }
    }

}