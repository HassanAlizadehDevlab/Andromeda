package com.android.common_test

import com.android.common.error.ErrorCode
import com.android.common.error.ErrorThrowable
import com.android.data.entity.model.remote.character.Character
import com.android.data.entity.model.remote.character.Response
import com.android.data.entity.model.remote.comic.Comic
import com.google.gson.Gson

/**
 * Created by hassanalizadeh on 30,August,2020
 */
object TestUtil {

    fun firstComicFromRemote(): Comic {
        return comicsFromRemote()
            .data
            .comics
            .get(0)
    }

    fun comicsFromRemote(): com.android.data.entity.model.remote.comic.Response {
        return Gson().fromJson(
            parseJson("comics.json"),
            com.android.data.entity.model.remote.comic.Response::class.java
        )
    }

    fun firstCharacterFromRemote(): Character {
        return charactersFromRemote()
            .data
            .characters
            .get(0)
    }

    fun charactersFromRemote(): Response {
        return Gson().fromJson(parseJson("characters.json"), Response::class.java)
    }

    private fun parseJson(fileName: String): String =
        javaClass.classLoader?.getResourceAsStream("json/$fileName")
            ?.bufferedReader().use { it?.readText().orEmpty() }

    fun error(): ErrorThrowable = ErrorThrowable(ErrorCode.ERROR_HAPPENED)
}