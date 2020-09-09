package com.android.data.repository.datasource.character

import androidx.annotation.VisibleForTesting
import com.android.data.entity.dao.CharacterDao
import com.android.data.entity.mapper.mapCharacters
import com.android.data.entity.model.local.CharacterEntity
import com.android.data.extension.onError
import com.android.data.network.DataServiceCharacter
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by hassanalizadeh on 29,August,2020
 */
open class SmartCharacterDataSource @Inject constructor(
    private val service: DataServiceCharacter,
    private val characterDao: CharacterDao
) : CharacterDataSource {

    @VisibleForTesting
    @Volatile
    var _page: Int = 1

    @VisibleForTesting
    @Volatile
    var _totalCount: Int = 0


    override fun characters(): Flowable<Pair<Int, List<CharacterEntity>>> {
        return characterDao.selectAll()
            .map {
                return@map if (_totalCount == 0)
                    it.size to it
                else
                    _totalCount to it
            }
            .onError()
    }

    override fun loadCharacters(): Completable {
        return resetPage()
            .andThen(getQueryParams())
            .flatMap { service.characters(it) }
            .flatMap { setTotalCount(it.data.total).toSingle { it } }
            .flatMap { clearCharacters().toSingle { it } }
            .flatMapCompletable { insertCharacters(it.data.characters.mapCharacters()) }
            .andThen(increasePage())
            .onError()
    }

    override fun loadMoreCharacters(): Completable {
        return getQueryParams()
            .flatMap { service.characters(it) }
            .flatMapCompletable { insertCharacters(it.data.characters.mapCharacters()) }
            .andThen(increasePage())
            .onError()
    }


    private fun insertCharacters(characters: List<CharacterEntity>?): Completable {
        if (characters.isNullOrEmpty()) return Completable.complete()
        return Completable.fromAction { characterDao.insert(characters) }
            .onError()
    }

    private fun clearCharacters(): Completable {
        return Completable.fromAction { characterDao.deleteAll() }
    }

    private fun setTotalCount(totalCount: Int): Completable {
        return Completable.fromAction { this._totalCount = totalCount }
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