package com.android.domain.usecase.character

import com.android.domain.executor.transformer.CTransformer
import com.android.domain.repository.CharacterRepository
import com.android.domain.usecase.UseCaseCompletable
import io.reactivex.Completable
import javax.inject.Inject

/**
 * Created by hassanalizadeh on 05,September,2020
 */
class RefreshCharactersUseCase @Inject constructor(
    private val repository: CharacterRepository,
    private val transformer: CTransformer
) : UseCaseCompletable<Unit>() {

    override fun execute(param: Unit): Completable {
        return repository.loadCharacters()
            .compose(transformer)
    }

}