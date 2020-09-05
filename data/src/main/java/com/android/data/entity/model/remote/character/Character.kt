package com.android.data.entity.model.remote.character

import com.android.data.entity.model.remote.Thumbnail

data class Character(
	val id: Int,
	val name: String,
	val thumbnail: Thumbnail
)