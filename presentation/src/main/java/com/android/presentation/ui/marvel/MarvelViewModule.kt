package com.android.presentation.ui.marvel

import com.android.presentation.common.di.FragmentScope
import dagger.Binds
import dagger.Module

/**
 * Created by hassanalizadeh on 06,September,2020
 */
@Module(includes = [MarvelPresenterModule::class])
abstract class MarvelViewModule {

    @Binds
    @FragmentScope
    abstract fun provideMarvelView(marvelViewImpl: MarvelViewImpl): MarvelView

}