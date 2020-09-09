package com.android.data.preference

import java.sql.Timestamp

/**
 * Created by hassanalizadeh on 09,September,2020
 */
interface PreferencesHelper {

    fun setLastTimeVisited(timestamp: Long)

    fun getLastTimeVisited(): Long

}