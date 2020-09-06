package com.android.domain.usecase.comic

import com.android.common_test.transformer.TestCTransformer
import com.android.domain.repository.ComicRepository
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Completable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

/**
 * Created by hassanalizadeh on 06,September,2020
 */
@RunWith(JUnit4::class)
class RefreshComicsUseCaseTest {


    @Mock
    private lateinit var repository: ComicRepository
    private lateinit var useCase: RefreshComicsUseCase

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        useCase = RefreshComicsUseCase(repository, TestCTransformer())
    }


    @Test
    fun execute() {
        // GIVEN
        val characterId = 4233
        Mockito.doReturn(Completable.complete()).whenever(repository).loadComics(anyInt())

        // WHEN
        useCase.invoke(characterId)
            .test()
            .assertComplete()

        // THEN
        Mockito.verify(repository).loadComics(characterId)
    }

}