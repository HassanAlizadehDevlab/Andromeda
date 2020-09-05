package com.android.data.network

import com.android.data.entity.model.remote.character.Response
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.QueryMap

/**
 * Created by hassanalizadeh on 28,August,2020
 */
interface DataServiceCharacter {

    @GET("public/characters")
    fun characters(
        @QueryMap map: Map<String, String>
    ): Single<Response>

}