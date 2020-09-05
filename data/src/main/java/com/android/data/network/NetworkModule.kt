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
    fun venuesDataService(dataServiceFactory: DataServiceFactory): DataServiceCharacter =
        dataServiceFactory.create(DataServiceCharacter::class.java)

}