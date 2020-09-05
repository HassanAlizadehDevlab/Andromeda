package com.android.andromeda

import com.android.data.DataModule
import com.android.domain.DomainModule
import com.android.presentation.PresentationModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import javax.inject.Singleton

/**
 * Project dagger component.
 */
@Singleton
@Component(
    modules = [
        AndromedaModule::class,
        DataModule::class,
        DomainModule::class,
        PresentationModule::class
    ]
)
interface AndromedaComponent : AndroidInjector<Andromeda> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Andromeda): AndromedaComponent
    }

}