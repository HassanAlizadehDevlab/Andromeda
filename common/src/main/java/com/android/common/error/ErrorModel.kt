package com.android.common.error

import com.google.gson.annotations.SerializedName

/**
 * Created by hassanalizadeh on 28,August,2020
 */
data class ErrorModel(@SerializedName("meta") val error: Error)