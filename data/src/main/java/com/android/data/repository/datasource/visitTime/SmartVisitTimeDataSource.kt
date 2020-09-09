package com.android.data.repository.datasource.visitTime

import com.android.data.preference.PreferencesHelper
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by hassanalizadeh on 09,September,2020
 */
open class SmartVisitTimeDataSource @Inject constructor(
    private val preferencesHelper: PreferencesHelper
) : VisitTimeDataSource {

    override fun lastVisitTime(): Single<String> {
        return Single.just(preferencesHelper.getLastTimeVisited())
    }

    override fun setLastVisitTime(time: String): Completable {
        return Completable.fromAction {
            preferencesHelper.setLastTimeVisited(time)
        }
    }

}