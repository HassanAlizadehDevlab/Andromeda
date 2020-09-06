package com.android.domain.usecase.comic

import com.android.domain.executor.transformer.CTransformer
import com.android.domain.repository.ComicRepository
import com.android.domain.usecase.UseCaseCompletable
import io.reactivex.Completable
import javax.inject.Inject

/**
 * Created by hassanalizadeh on 06,September,2020
 */
class RefreshComicsUseCase @Inject constructor(
    private val repository: ComicRepository,
    private val transformer: CTransformer
) : UseCaseCompletable<Int>() {

    /**
     * @param param is character id
     * */
    override fun execute(param: Int): Completable {
        return repository.loadComics(param)
            .compose(transformer)
    }

}