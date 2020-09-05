package com.android.andromeda

import android.app.Application
import com.android.presentation.common.di.ActivityScope
import com.android.presentation.ui.MainActivity
import com.android.presentation.ui.MainActivityModule
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjectionModule
import dagger.android.ContributesAndroidInjector
import javax.inject.Singleton

/**
 * Created by hassanalizadeh on 27,August,2020
 */
@Module(includes = [AndroidInjectionModule::class])
abstract class AndromedaModule {

    @Binds
    @Singleton
    abstract fun application(application: Andromeda): Application

    @ActivityScope
    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun mainActivityInjector(): MainActivity

}