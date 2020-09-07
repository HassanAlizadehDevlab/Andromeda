package com.android.data.entity.model.remote.comic

import com.android.data.entity.model.remote.Thumbnail

/**
 * Created by hassanalizadeh on 05,September,2020
 */
data class Comic(
    val id: Int,
    val title: String,
    val description: String?,
    val thumbnail: Thumbnail
)