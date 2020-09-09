package com.android.domain.repository

import io.reactivex.Completable
import io.reactivex.Single

/**
 * Created by hassanalizadeh on 09,September,2020
 */
interface VisitTimeRepository {
    fun lastVisitTime(): Single<String>
    fun setVisitTime(time: String): Completable
}