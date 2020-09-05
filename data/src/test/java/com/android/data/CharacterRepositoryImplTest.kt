package com.android.data

import com.android.common_test.TestUtil
import com.android.data.entity.mapper.mapCharacters
import com.android.data.repository.CharacterRepositoryImpl
import com.android.data.repository.datasource.character.CharacterDataSource
import com.android.domain.repository.CharacterRepository
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Completable
import io.reactivex.Flowable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.MockitoAnnotations

/**
 * Created by hassanalizadeh on 30,August,2020
 */
@RunWith(JUnit4::class)
class CharacterRepositoryImplTest {

    @Mock
    private lateinit var dataSource: CharacterDataSource
    private lateinit var repository: CharacterRepository


    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        repository = CharacterRepositoryImpl(dataSource)
    }

    @Test
    fun `get characters`() {
        //GIVEN
        doReturn(Flowable.just(Pair(0, TestUtil.charactersFromRemote().data.characters.mapCharacters())))
            .whenever(dataSource).characters()

        //WHEN
        repository.characters()
            .test()
            .assertValue {
                it.items[0].id == TestUtil.firstCharacterFromRemote().id
            }
            .assertComplete()

        //THEN
        verify(dataSource).characters()
    }

    @Test
    fun `load characters`() {
        //GIVEN
        doReturn(Completable.complete()).whenever(dataSource).loadCharacters()

        //WHEN
        repository.loadCharacters()
            .test()
            .assertComplete()

        //THEN
        verify(dataSource).loadCharacters()
    }

    @Test
    fun `load more characterss`() {
        //GIVEN
        doReturn(Completable.complete()).whenever(dataSource).loadMoreCharacters()

        //WHEN
        repository.loadMoreCharacters()
            .test()
            .assertComplete()

        //THEN
        verify(dataSource).loadMoreCharacters()
    }

}