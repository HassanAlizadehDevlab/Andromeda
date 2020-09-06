package com.android.presentation.common.view

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.android.presentation.common.di.ActivityScope
import dagger.Binds
import dagger.Module

@Module
abstract class BaseActivityModule {

    @Binds
    @ActivityScope
    abstract fun activityContext(appCompatActivity: AppCompatActivity): Context

}