package com.android.data.repository.datasource

import com.android.data.repository.datasource.character.CharacterDataSource
import com.android.data.repository.datasource.character.SmartCharacterDataSource
import com.android.data.repository.datasource.comic.ComicDataSource
import com.android.data.repository.datasource.comic.SmartComicDataSource
import com.android.data.repository.datasource.visitTime.SmartVisitTimeDataSource
import com.android.data.repository.datasource.visitTime.VisitTimeDataSource
import dagger.Binds
import dagger.Module
import dagger.Reusable

/**
 * Created by hassanalizadeh on 29,August,2020
 */
@Module
abstract class DataSourceModule {

    @Binds
    @Reusable
    abstract fun provideCharactersDataSource(
        smartCharacterDataSource: SmartCharacterDataSource
    ): CharacterDataSource

    @Binds
    @Reusable
    abstract fun provideComicsDataSource(
        smartComicDataSource: SmartComicDataSource
    ): ComicDataSource

    @Binds
    @Reusable
    abstract fun provideVisitTimeDataSource(
        smartVisitTimeDataSource: SmartVisitTimeDataSource
    ): VisitTimeDataSource

}