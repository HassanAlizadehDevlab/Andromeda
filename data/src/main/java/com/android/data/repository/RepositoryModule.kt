package com.android.data.repository

import com.android.data.repository.datasource.DataSourceModule
import com.android.domain.repository.CharacterRepository
import com.android.domain.repository.ComicRepository
import com.android.domain.repository.VisitTimeRepository
import dagger.Binds
import dagger.Module
import dagger.Reusable

/**
 * Created by hassanalizadeh on 29,August,2020
 */
@Module(includes = [DataSourceModule::class])
abstract class RepositoryModule {

    @Binds
    @Reusable
    abstract fun provideCharacterRepository(
        characterRepositoryImpl: CharacterRepositoryImpl
    ): CharacterRepository

    @Binds
    @Reusable
    abstract fun provideComicRepository(
        comicRepositoryImpl: ComicRepositoryImpl
    ): ComicRepository

    @Binds
    @Reusable
    abstract fun provideVisitTimeRepository(
        visitTimeRepositoryImpl: VisitTimeRepositoryImpl
    ): VisitTimeRepository

}