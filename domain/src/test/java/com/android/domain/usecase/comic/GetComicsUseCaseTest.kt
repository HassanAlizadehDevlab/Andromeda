package com.android.domain.usecase.comic

import com.android.common_test.TestUtil
import com.android.common_test.transformer.TestFTransformer
import com.android.data.entity.mapper.map
import com.android.data.entity.mapper.mapComics
import com.android.domain.entity.ComicsObject
import com.android.domain.repository.ComicRepository
import com.android.domain.usecase.invoke
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Flowable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

/**
 * Created by hassanalizadeh on 06,September,2020
 */
@RunWith(JUnit4::class)
class GetComicsUseCaseTest {


    @Mock
    private lateinit var repository: ComicRepository
    private lateinit var useCase: GetComicsUseCase

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        useCase = GetComicsUseCase(repository, TestFTransformer())
    }


    @Test
    fun execute() {
        // GIVEN
        val expected = TestUtil.firstComicFromRemote().id
        Mockito.doReturn(
            Flowable.just(
                ComicsObject(
                    totalCount = 0,
                    items = mutableListOf(TestUtil.firstComicFromRemote().map().mapComics())
                )
            )
        ).whenever(repository).comics()

        // WHEN
        useCase.invoke()
            .test()
            .assertValue {
                it.items[0].id == expected
            }
            .assertComplete()

        // THEN
        Mockito.verify(repository).comics()
    }

}