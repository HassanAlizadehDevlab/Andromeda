package com.android.data.repository.datasource.visitTime

import io.reactivex.Completable
import io.reactivex.Single

/**
 * Created by hassanalizadeh on 09,September,2020
 */
interface VisitTimeDataSource {
    fun lastVisitTime(): Single<String>
    fun setLastVisitTime(time: String): Completable
}