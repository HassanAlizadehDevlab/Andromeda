package com.android.data.preference

/**
 * Created by hassanalizadeh on 09,September,2020
 */
interface PreferencesHelper {

    fun setLastTimeVisited(time: String)

    fun getLastTimeVisited(): String

}