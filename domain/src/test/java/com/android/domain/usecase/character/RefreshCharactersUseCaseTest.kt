package com.android.domain.usecase.character

import com.android.common_test.transformer.TestCTransformer
import com.android.domain.repository.CharacterRepository
import com.android.domain.repository.VisitTimeRepository
import com.android.domain.usecase.invoke
import com.nhaarman.mockitokotlin2.never
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Completable
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import java.text.SimpleDateFormat
import java.util.*


/**
 * Created by hassanalizadeh on 05,September,2020
 */
@RunWith(JUnit4::class)
class RefreshCharactersUseCaseTest {


    @Mock
    private lateinit var characterRepository: CharacterRepository

    @Mock
    private lateinit var visitTimeRepository: VisitTimeRepository
    private lateinit var useCase: RefreshCharactersUseCase

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        useCase = RefreshCharactersUseCase(
            visitTimeRepository,
            characterRepository,
            TestCTransformer()
        )
    }

    @Test
    fun `execute when last visited time is blank`() {
        // GIVEN
        Mockito.doReturn(Single.just("")).whenever(visitTimeRepository).lastVisitTime()
        Mockito.doReturn(Completable.complete()).whenever(visitTimeRepository).setVisitTime(anyString())
        Mockito.doReturn(Completable.complete()).whenever(characterRepository).loadCharacters()

        // WHEN
        useCase.invoke()
            .test()
            .assertComplete()

        // THEN
        verify(visitTimeRepository).lastVisitTime()
        verify(characterRepository).loadCharacters()
        verify(visitTimeRepository).setVisitTime(anyString())
    }


    @Test
    fun `execute before 24 hours`() {
        // GIVEN
        Mockito.doReturn(Single.just(lessThan24Hours())).whenever(visitTimeRepository)
            .lastVisitTime()

        // WHEN
        useCase.invoke()
            .test()
            .assertComplete()

        // THEN
        verify(visitTimeRepository).lastVisitTime()
        verify(characterRepository, never()).loadCharacters()
        verify(visitTimeRepository, never()).setVisitTime(anyString())
    }


    @Test
    fun `execute after 24 hours`() {
        // GIVEN
        Mockito.doReturn(Single.just(moreThan24Hours())).whenever(visitTimeRepository)
            .lastVisitTime()
        Mockito.doReturn(Completable.complete()).whenever(visitTimeRepository).setVisitTime(anyString())
        Mockito.doReturn(Completable.complete()).whenever(characterRepository).loadCharacters()

        // WHEN
        useCase.invoke()
            .test()
            .assertComplete()

        // THEN
        verify(visitTimeRepository).lastVisitTime()
        verify(characterRepository).loadCharacters()
        verify(visitTimeRepository).setVisitTime(anyString())
    }



    private fun lessThan24Hours(): String {
        val cal = Calendar.getInstance()
        val sdfDate = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        cal.time = Date()
        cal.add(Calendar.HOUR, -10)
        val twoDaysAgo = cal.time
        return sdfDate.format(twoDaysAgo)
    }

    private fun moreThan24Hours(): String {
        val cal = Calendar.getInstance()
        val sdfDate = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        cal.time = Date()
        cal.add(Calendar.DATE, -2)
        val twoDaysAgo = cal.time
        return sdfDate.format(twoDaysAgo)
    }

}