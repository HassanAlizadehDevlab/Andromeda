package com.android.domain.usecase.character

import com.android.domain.executor.transformer.CTransformer
import com.android.domain.repository.CharacterRepository
import com.android.domain.repository.VisitTimeRepository
import com.android.domain.usecase.UseCaseCompletable
import io.reactivex.Completable
import io.reactivex.Single
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import kotlin.math.abs

/**
 * Created by hassanalizadeh on 05,September,2020
 */
class RefreshCharactersUseCase @Inject constructor(
    private val visitTimeRepository: VisitTimeRepository,
    private val characterRepository: CharacterRepository,
    private val transformer: CTransformer
) : UseCaseCompletable<Unit>() {

    override fun execute(param: Unit): Completable {
        return visitTimeRepository.lastVisitTime()
            .flatMap { compareTime(it) }
            .flatMap { hours ->
                if (hours >= HOURS_IN_DAY)
                    characterRepository.loadCharacters().toSingle { true }
                else Completable.complete().toSingle { false }
            }
            .flatMapCompletable { shouldInsertNewVisitTime ->
                if (shouldInsertNewVisitTime) visitTimeRepository.setVisitTime(currentTime())
                else Completable.complete()
            }
            .compose(transformer)
    }

    private fun compareTime(lastVisitTime: String): Single<Long> {
        return Single.fromCallable {
            if (lastVisitTime.isBlank()) return@fromCallable HOURS_IN_DAY.toLong()

            val sdf = SimpleDateFormat(DATE_TIME_PATTERN)
            val firstDate: Date = sdf.parse(lastVisitTime)
            val secondDate: Date = sdf.parse(currentTime())
            val diffInMillies = abs(secondDate.time - firstDate.time)

            return@fromCallable TimeUnit.HOURS.convert(diffInMillies, TimeUnit.MILLISECONDS)
        }
    }

    private fun currentTime(): String {
        val sdf = SimpleDateFormat(DATE_TIME_PATTERN)
        val now = Date()
        return sdf.format(now)
    }

    companion object {
        private const val DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss"
        private const val HOURS_IN_DAY = 24
    }

}