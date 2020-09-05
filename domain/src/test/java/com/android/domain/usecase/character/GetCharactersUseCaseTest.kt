package com.android.domain.usecase.character

import com.android.common_test.TestUtil
import com.android.common_test.transformer.TestFTransformer
import com.android.data.entity.mapper.map
import com.android.domain.entity.CharactersObject
import com.android.domain.repository.CharacterRepository
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
 * Created by hassanalizadeh on 05,September,2020
 */
@RunWith(JUnit4::class)
class GetCharactersUseCaseTest {


    @Mock
    private lateinit var repository: CharacterRepository
    private lateinit var useCase: GetCharactersUseCase

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        useCase = GetCharactersUseCase(repository, TestFTransformer())
    }


    @Test
    fun execute() {
        // GIVEN
        val expected = TestUtil.firstCharacterFromRemote().id
        Mockito.doReturn(
            Flowable.just(
                CharactersObject(
                    totalCount = 0,
                    items = mutableListOf(TestUtil.firstCharacterFromRemote().map().map())
                )
            )
        ).whenever(repository).characters()

        // WHEN
        useCase.invoke()
            .test()
            .assertValue {
                it.items[0].id == expected
            }
            .assertComplete()

        // THEN
        Mockito.verify(repository).characters()
    }

}