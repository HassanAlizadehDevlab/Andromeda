package com.android.domain

import com.android.domain.entity.CharactersObject
import com.android.domain.entity.ComicsObject
import com.android.domain.executor.transformer.AsyncCTransformer
import com.android.domain.executor.transformer.AsyncFTransformer
import com.android.domain.executor.transformer.CTransformer
import com.android.domain.executor.transformer.FTransformer
import dagger.Binds
import dagger.Module

/**
 * Created by hassanalizadeh on 27,August,2020
 */
@Module
abstract class DomainModule {

    @Binds
    abstract fun completableTransformer(
        transformer: AsyncCTransformer
    ): CTransformer

    @Binds
    abstract fun charactersTransformer(
        transformer: AsyncFTransformer<CharactersObject>
    ): FTransformer<CharactersObject>

    @Binds
    abstract fun comicsTransformer(
        transformer: AsyncFTransformer<ComicsObject>
    ): FTransformer<ComicsObject>

}