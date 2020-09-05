package com.android.data.entity.model.remote.comic

import com.google.gson.annotations.SerializedName

/**
 * Created by hassanalizadeh on 05,September,2020
 */
data class Data(
    val total: Int,
    @SerializedName("result")
    val comics: List<Comic>
)