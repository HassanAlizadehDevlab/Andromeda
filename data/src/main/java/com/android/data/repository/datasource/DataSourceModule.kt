package com.android.data.repository.datasource

import com.android.data.repository.datasource.character.CharacterDataSource
import com.android.data.repository.datasource.character.SmartCharacterDataSource
import com.android.data.repository.datasource.comic.ComicDataSource
import com.android.data.repository.datasource.comic.SmartComicDataSource
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

}