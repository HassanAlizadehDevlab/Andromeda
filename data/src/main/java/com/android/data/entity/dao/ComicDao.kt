package com.android.data.entity.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.android.data.entity.model.local.ComicEntity
import io.reactivex.Flowable
import io.reactivex.Single

/**
 * Created by hassanalizadeh on 05,September,2020
 */
@Dao
interface ComicDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(comics: List<ComicEntity>)

    @Query("SELECT * FROM comic WHERE id=:id")
    fun selectById(id: Int): Single<ComicEntity>

    @Query("SELECT * FROM comic")
    fun selectAll(): Flowable<List<ComicEntity>>

    @Query("DELETE FROM comic")
    fun deleteAll()

}