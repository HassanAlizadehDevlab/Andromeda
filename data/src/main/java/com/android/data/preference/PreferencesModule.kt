package com.android.data.preference

import dagger.Binds
import dagger.Module
import javax.inject.Singleton

/**
 * Created by hassanalizadeh on 09,September,2020
 */
@Module
abstract class PreferencesModule {

    @Binds
    @Singleton
    abstract fun appPreferences(appPreferences: AppPreferences): PreferencesHelper

}