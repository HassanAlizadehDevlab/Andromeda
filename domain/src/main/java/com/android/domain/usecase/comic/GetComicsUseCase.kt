package com.android.domain.usecase.comic

import com.android.domain.entity.ComicsObject
import com.android.domain.executor.transformer.FTransformer
import com.android.domain.repository.ComicRepository
import com.android.domain.usecase.UseCaseFlowable
import io.reactivex.Flowable
import javax.inject.Inject

/**
 * Created by hassanalizadeh on 06,September,2020
 */
class GetComicsUseCase @Inject constructor(
    private val repository: ComicRepository,
    private val transformer: FTransformer<ComicsObject>
) : UseCaseFlowable<ComicsObject, Unit>() {

    override fun execute(param: Unit): Flowable<ComicsObject> {
        return repository.comics()
            .compose(transformer)
    }

}