package com.android.data.entity.model.local

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by hassanalizadeh on 05,September,2020
 */
@Entity(tableName = "comic")
data class ComicEntity(
    @PrimaryKey
    val id: Int,
    val title: String,
    val variantDescription: String,
    @Embedded(prefix = "thumbnail_")
    val thumbnail: ThumbnailEntity
)