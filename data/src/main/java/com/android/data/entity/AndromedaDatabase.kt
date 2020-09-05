package com.android.data.entity

import androidx.room.Database
import androidx.room.RoomDatabase
import com.android.data.entity.dao.CharacterDao
import com.android.data.entity.model.local.CharacterEntity

/**
 * The FourSquare's Database.
 */
@Database(
    entities = [CharacterEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AndromedaDatabase : RoomDatabase() {

    abstract fun characterDao(): CharacterDao

}