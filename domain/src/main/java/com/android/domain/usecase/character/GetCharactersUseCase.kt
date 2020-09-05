package com.android.domain.usecase.character

import com.android.domain.entity.CharactersObject
import com.android.domain.executor.transformer.FTransformer
import com.android.domain.repository.CharacterRepository
import com.android.domain.usecase.UseCaseFlowable
import io.reactivex.Flowable
import javax.inject.Inject

/**
 * Created by hassanalizadeh on 05,September,2020
 */
class GetCharactersUseCase @Inject constructor(
    private val repository: CharacterRepository,
    private val transformer: FTransformer<CharactersObject>
) : UseCaseFlowable<CharactersObject, Unit>() {

    override fun execute(param: Unit): Flowable<CharactersObject> {
        return repository.characters()
            .compose(transformer)
    }

}