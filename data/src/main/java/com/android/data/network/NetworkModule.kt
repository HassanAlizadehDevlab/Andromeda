package com.android.data.network

import dagger.Module
import dagger.Provides
import dagger.Reusable

/**
 * Remote API provider.
 */
@Module
class NetworkModule {

    @Provides
    @Reusable
    fun characterDataService(dataServiceFactory: DataServiceFactory): DataServiceCharacter =
        dataServiceFactory.create(DataServiceCharacter::class.java)

}