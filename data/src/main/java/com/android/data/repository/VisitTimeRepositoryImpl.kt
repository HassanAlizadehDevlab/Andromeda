package com.android.data.repository

import com.android.data.repository.datasource.visitTime.VisitTimeDataSource
import com.android.domain.repository.VisitTimeRepository
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by hassanalizadeh on 05,September,2020
 */
class VisitTimeRepositoryImpl @Inject constructor(
    private val dataSource: VisitTimeDataSource
) : VisitTimeRepository {

    override fun lastVisitTime(): Single<String> {
        return dataSource.lastVisitTime()
    }

    override fun setVisitTime(time: String): Completable {
        return dataSource.setLastVisitTime(time)
    }
}