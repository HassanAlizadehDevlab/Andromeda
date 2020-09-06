package com.android.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.android.presentation.common.di.ActivityScope
import com.android.presentation.common.di.FragmentScope
import com.android.presentation.common.view.BaseActivityModule
import com.android.presentation.ui.marvel.MarvelViewImpl
import com.android.presentation.ui.marvel.MarvelViewModule
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import javax.inject.Named

/**
 * Created by hassanalizadeh on 28,August,2020
 */
@Module(
    includes = [
        BaseActivityModule::class
    ]
)
abstract class MainActivityModule {

    @FragmentScope
    @ContributesAndroidInjector(modules = [MarvelViewModule::class])
    abstract fun marvelViewInjector(): MarvelViewImpl

    @Binds
    @ActivityScope
    abstract fun activity(mainActivity: MainActivity): AppCompatActivity

    @Module
    companion object {

        const val ACTIVITY_FRAGMENT_MANAGER = "MainActivityFragmentManager"

        @JvmStatic
        @Provides
        @Named(ACTIVITY_FRAGMENT_MANAGER)
        @ActivityScope
        fun provideMainActivityFragmentManager(mainActivity: MainActivity): FragmentManager {
            return mainActivity.supportFragmentManager
        }

    }

}