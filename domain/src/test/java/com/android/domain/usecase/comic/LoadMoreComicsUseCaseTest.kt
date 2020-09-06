package com.android.domain.usecase.comic

import com.android.common_test.transformer.TestCTransformer
import com.android.domain.repository.ComicRepository
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
class LoadMoreComicsUseCaseTest {

    @Mock
    private lateinit var repository: ComicRepository
    private lateinit var useCase: LoadMoreComicsUseCase

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        useCase = LoadMoreComicsUseCase(repository, TestCTransformer())
    }


    @Test
    fun execute() {
        // GIVEN
        Mockito.doReturn(Completable.complete()).whenever(repository).loadMoreComics()

        // WHEN
        useCase.invoke()
            .test()
            .assertComplete()

        // THEN
        Mockito.verify(repository).loadMoreComics()
    }

}