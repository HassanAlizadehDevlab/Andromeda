package com.android.presentation.common.view

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.android.presentation.common.di.ActivityScope
import com.android.presentation.common.viewmodel.ViewModelProviderFactory
import dagger.Binds
import dagger.Module

@Module
abstract class BaseActivityModule {

    @Binds
    @ActivityScope
    abstract fun activityContext(appCompatActivity: AppCompatActivity): Context

    /**
     * Bind it just once
     * This is used with Dagger MultiBinding
     * */
    @Binds
    abstract fun bindViewModelFactory(viewModelProviderFactory: ViewModelProviderFactory): ViewModelProvider.Factory

}