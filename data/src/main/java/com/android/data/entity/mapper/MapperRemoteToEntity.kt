package com.android.data.entity.mapper

import com.android.data.entity.model.local.CharacterEntity
import com.android.data.entity.model.local.ComicEntity
import com.android.data.entity.model.local.ThumbnailEntity
import com.android.data.entity.model.remote.Thumbnail
import com.android.data.entity.model.remote.character.Character
import com.android.data.entity.model.remote.comic.Comic

/**
 * Created by hassanalizadeh on 05,September,2020
 */
fun List<Character>.mapCharacters(): List<CharacterEntity> = map { it.map() }

fun Character.map(): CharacterEntity = CharacterEntity(
    id = id,
    name = name,
    thumbnail = thumbnail.map()
)

fun Thumbnail.map(): ThumbnailEntity = ThumbnailEntity(
    path = path,
    extension = extension
)

fun List<Comic>.mapComics(): List<ComicEntity> = map { it.map() }

fun Comic.map(): ComicEntity = ComicEntity(
    id = id,
    title = title,
    variantDescription = variantDescription,
    thumbnail = thumbnail.map()
)