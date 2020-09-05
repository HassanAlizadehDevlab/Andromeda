package com.android.data.entity.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.android.data.entity.model.local.CharacterEntity
import io.reactivex.Flowable
import io.reactivex.Single

/**
 * Created by hassanalizadeh on 05,September,2020
 */
@Dao
interface CharacterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(characters: List<CharacterEntity>)

    @Query("SELECT * FROM character WHERE id=:id")
    fun selectById(id: Int): Single<CharacterEntity>

    @Query("SELECT * FROM character")
    fun selectAll(): Flowable<List<CharacterEntity>>

    @Query("DELETE FROM character")
    fun deleteAll()

}