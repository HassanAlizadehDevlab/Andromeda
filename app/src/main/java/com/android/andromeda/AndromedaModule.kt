package com.android.andromeda

import android.app.Application
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

/**
 * Created by hassanalizadeh on 27,August,2020
 */
@Module(includes = [AndroidInjectionModule::class])
abstract class AndromedaModule {

    @Binds
    @Singleton
    abstract fun application(application: Andromeda): Application

}