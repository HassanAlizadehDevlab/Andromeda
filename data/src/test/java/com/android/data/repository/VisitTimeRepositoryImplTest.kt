package com.android.data.repository

import com.android.data.repository.datasource.visitTime.VisitTimeDataSource
import com.android.domain.repository.VisitTimeRepository
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Completable
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentMatchers.anyLong
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

/**
 * Created by hassanalizadeh on 09,September,2020
 */
@RunWith(JUnit4::class)
class VisitTimeRepositoryImplTest {

    @Mock
    private lateinit var dataSource: VisitTimeDataSource
    private lateinit var repository: VisitTimeRepository


    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        repository = VisitTimeRepositoryImpl(dataSource)
    }


    @Test
    fun `get last visited timestamp`() {
        // GIVEN
        val timestamp = 43663472864827
        Mockito.doReturn(Single.just(timestamp))
            .whenever(dataSource)
            .lastVisitTime()

        // WHEN
        repository.lastVisitTime()
            .test()
            .assertValue(timestamp)
            .assertComplete()

        // THEN
        Mockito.verify(dataSource).lastVisitTime()
    }


    @Test
    fun `set last visited timestamp`() {
        // GIVEN
        val timestamp = 43663472864827
        Mockito.doReturn(Completable.complete())
            .whenever(dataSource)
            .setLastVisitTime(anyLong())

        // WHEN
        repository.setVisitTime(timestamp)
            .test()
            .assertComplete()

        // THEN
        Mockito.verify(dataSource).setLastVisitTime(timestamp)
    }

}