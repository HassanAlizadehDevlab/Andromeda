package com.android.data.repository.datasource.visitTime

import com.android.data.preference.PreferencesHelper
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

/**
 * Created by hassanalizadeh on 09,September,2020
 */
@RunWith(JUnit4::class)
class SmartVisitTimeDataSourceTest {

    @Mock
    private lateinit var preferencesHelper: PreferencesHelper
    private lateinit var smartVisitTimeDataSource: SmartVisitTimeDataSource


    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        smartVisitTimeDataSource = Mockito.spy(
            SmartVisitTimeDataSource(
                preferencesHelper
            )
        )
    }


    @Test
    fun `get last visited timestamp`() {
        // GIVEN
        val timestamp = 43663472864827
        Mockito.doReturn(timestamp)
            .whenever(preferencesHelper)
            .getLastTimeVisited()

        // WHEN
        smartVisitTimeDataSource.lastVisitTime()
            .test()
            .assertValue(timestamp)
            .assertComplete()

        // THEN
        verify(preferencesHelper).getLastTimeVisited()
    }


    @Test
    fun `set last visited timestamp`() {
        // GIVEN
        val timestamp = 43663472864827

        // WHEN
        smartVisitTimeDataSource.setLastVisitTime(timestamp)
            .test()
            .assertComplete()

        // THEN
        verify(preferencesHelper).setLastTimeVisited(timestamp)
    }

}