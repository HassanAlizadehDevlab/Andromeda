package com.android.domain.usecase.comic

import com.android.domain.executor.transformer.CTransformer
import com.android.domain.repository.ComicRepository
import com.android.domain.usecase.UseCaseCompletable
import io.reactivex.Completable
import javax.inject.Inject

/**
 * Created by hassanalizadeh on 30,August,2020
 */
class LoadMoreComicsUseCase @Inject constructor(
    private val repository: ComicRepository,
    private val transformer: CTransformer
) : UseCaseCompletable<Unit>() {

    override fun execute(param: Unit): Completable {
        return repository.loadMoreComics()
            .compose(transformer)
    }

}