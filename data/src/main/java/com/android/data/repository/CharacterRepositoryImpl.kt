package com.android.data.repository

import com.android.data.entity.mapper.map
import com.android.data.repository.datasource.character.CharacterDataSource
import com.android.domain.entity.CharactersObject
import com.android.domain.repository.CharacterRepository
import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject

/**
 * Created by hassanalizadeh on 05,September,2020
 */
class CharacterRepositoryImpl @Inject constructor(
    private val dataSource: CharacterDataSource
) : CharacterRepository {

    override fun characters(): Flowable<CharactersObject> {
        return dataSource.characters().map {
            return@map CharactersObject(it.first, it.second.map())
        }
    }

    override fun loadCharacters(): Completable {
        return dataSource.loadCharacters()
    }

    override fun loadMoreCharacters(): Completable {
        return dataSource.loadMoreCharacters()
    }

}