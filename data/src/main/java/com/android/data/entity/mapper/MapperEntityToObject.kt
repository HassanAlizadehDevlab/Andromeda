package com.android.data.entity.mapper

import com.android.data.entity.model.local.CharacterEntity
import com.android.data.entity.model.local.ComicEntity
import com.android.data.entity.model.local.ThumbnailEntity
import com.android.domain.entity.CharacterObject
import com.android.domain.entity.ComicObject

/**
 * Created by hassanalizadeh on 05,September,2020
 */
fun List<CharacterEntity>.map(): List<CharacterObject> = map { it.map() }

fun CharacterEntity.map(): CharacterObject = CharacterObject(
    id = id,
    name = name,
    thumbnail = thumbnail.map()
)

fun ThumbnailEntity.map(): String = this.path.plus(this.extension)

fun List<ComicEntity>.mapComics(): List<ComicObject> = map { it.mapComics() }

fun ComicEntity.mapComics(): ComicObject = ComicObject(
    id = id,
    title = title,
    variantDescription = variantDescription,
    thumbnail = thumbnail.map()
)
