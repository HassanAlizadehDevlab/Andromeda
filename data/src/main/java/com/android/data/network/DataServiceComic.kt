package com.android.data.network

import com.android.data.entity.model.remote.comic.Response
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

/**
 * Created by hassanalizadeh on 28,August,2020
 */
interface DataServiceComic {

    @GET("public/characters/{ID}/comics")
    fun comics(
        @Path("ID") id: Int,
        @QueryMap map: Map<String, String>
    ): Single<Response>

}