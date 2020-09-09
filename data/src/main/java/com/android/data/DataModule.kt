package com.android.data

import com.android.data.entity.EntityModule
import com.android.data.executor.ExecutionModule
import com.android.data.network.NetworkModule
import com.android.data.preference.PreferencesModule
import com.android.data.repository.RepositoryModule
import dagger.Module

/**
 * Created by hassanalizadeh on 27,August,2020
 */
@Module(
    includes = [
        ExecutionModule::class,
        EntityModule::class,
        NetworkModule::class,
        RepositoryModule::class,
        PreferencesModule::class
    ]
)
abstract class DataModule