package com.android.domain.usecase.character

import com.android.common_test.transformer.TestCTransformer
import com.android.domain.repository.CharacterRepository
import com.android.domain.usecase.invoke
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Completable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

/**
 * Created by hassanalizadeh on 05,September,2020
 */
@RunWith(JUnit4::class)
class RefreshCharactersUseCaseTest {


    @Mock
    private lateinit var repository: CharacterRepository
    private lateinit var useCase: RefreshCharactersUseCase

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        useCase = RefreshCharactersUseCase(repository, TestCTransformer())
    }


    @Test
    fun execute() {
        // GIVEN
        Mockito.doReturn(Completable.complete()).whenever(repository).loadCharacters()

        // WHEN
        useCase.invoke()
            .test()
            .assertComplete()

        // THEN
        Mockito.verify(repository).loadCharacters()
    }

}