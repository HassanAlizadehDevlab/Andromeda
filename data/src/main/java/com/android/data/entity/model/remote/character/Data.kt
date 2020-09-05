package com.android.data.entity.model.remote.character

import com.google.gson.annotations.SerializedName

data class Data(
	val total: Int,
	@SerializedName("results")
	val characters: List<Character>
)