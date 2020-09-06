package com.android.presentation.ui.marvel

import com.android.presentation.common.di.FragmentScope
import dagger.Binds
import dagger.Module

/**
 * Created by hassanalizadeh on 07,September,2020
 */
@Module
abstract class MarvelPresenterModule {

    @Binds
    @FragmentScope
    abstract fun provideMarvelPresenter(marvelPresenterImpl: MarvelPresenterImpl): MarvelPresenter

}