package com.android.data.entity

import android.app.Application
import androidx.room.Room
import com.android.data.entity.dao.CharacterDao
import com.android.data.entity.dao.ComicDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Entity provider module.
 */
@Module
class EntityModule {

    @Provides
    fun characterDao(db: AndromedaDatabase): CharacterDao = db.characterDao()

    @Provides
    fun comicDao(db: AndromedaDatabase): ComicDao = db.comicDao()

    @Provides
    @Singleton
    fun database(application: Application): AndromedaDatabase = Room.databaseBuilder(
        application.applicationContext,
        AndromedaDatabase::class.java,
        "andromeda_db"
    ).build()

}