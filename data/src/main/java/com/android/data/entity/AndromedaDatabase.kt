package com.android.data.entity

import androidx.room.Database
import androidx.room.RoomDatabase
import com.android.data.entity.dao.CharacterDao
import com.android.data.entity.dao.ComicDao
import com.android.data.entity.model.local.CharacterEntity
import com.android.data.entity.model.local.ComicEntity

/**
 * The FourSquare's Database.
 */
@Database(
    entities = [
        CharacterEntity::class,
        ComicEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AndromedaDatabase : RoomDatabase() {

    abstract fun characterDao(): CharacterDao

    abstract fun comicDao(): ComicDao

}