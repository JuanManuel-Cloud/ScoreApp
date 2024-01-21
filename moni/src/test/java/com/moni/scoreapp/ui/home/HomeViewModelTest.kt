package com.moni.scoreapp.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.moni.scoreapp.MainCoroutineRule
import com.moni.scoreapp.data.local.enums.Genders
import com.moni.scoreapp.data.local.enums.RecordStatus
import com.moni.scoreapp.data.remote.models.Apellido
import com.moni.scoreapp.data.remote.models.Dni
import com.moni.scoreapp.data.remote.models.Email
import com.moni.scoreapp.data.remote.models.Fields
import com.moni.scoreapp.data.remote.models.Genero
import com.moni.scoreapp.data.remote.models.Nombre
import com.moni.scoreapp.data.remote.models.RecordRq
import com.moni.scoreapp.data.remote.models.Status
import com.moni.scoreapp.getOrAwaitValueTest
import com.moni.scoreapp.repositories.FakeScoreRepository
import com.moni.scoreapp.utils.ResStatus
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeViewModelTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()


    private lateinit var viewModel: HomeViewModel
    private lateinit var fakeScoreRepository: FakeScoreRepository
    private lateinit var recordRq: RecordRq

    @Before
    fun setUp() {
        fakeScoreRepository = FakeScoreRepository()
        fakeScoreRepository.setShouldReturnNetworkError(true)
        viewModel = HomeViewModel(fakeScoreRepository)

        recordRq = RecordRq(
            Fields(
                nombre = Nombre("Fernando"),
                apellido = Apellido("Alonso"),
                email = Email("fernandoalonsof1@formula1.com"),
                dni = Dni("12121212"),
                genero = Genero(Genders.MALE),
                status = Status(RecordStatus.APPROVE)
            )
        )
    }

    @After
    fun tearDown() {
    }

    @Test
    fun `getScore api error`() {
        viewModel.getScore("12345678")

        val value = viewModel.scoreStatus.getOrAwaitValueTest()

        assertEquals(value.getContentIfNotHandled()?.status, ResStatus.ERROR)
    }

    @Test
    fun `getScore api success`() {
        fakeScoreRepository.setShouldReturnNetworkError(false)

        viewModel.getScore("12345678")

        val value = viewModel.scoreStatus.getOrAwaitValueTest()

        assertEquals(value.getContentIfNotHandled()?.status, ResStatus.SUCCESS)
    }

    @Test
    fun `createRecordFirebase api error`() {
        viewModel.createRecordFirebase(recordRq)

        val value = viewModel.recordStatus.getOrAwaitValueTest()

        assertEquals(value.getContentIfNotHandled()?.status, ResStatus.ERROR)
    }

    @Test
    fun `createRecordFirebase api success`() {
        fakeScoreRepository.setShouldReturnNetworkError(false)

        viewModel.createRecordFirebase(recordRq)

        val value = viewModel.recordStatus.getOrAwaitValueTest()

        assertEquals(value.getContentIfNotHandled()?.status, ResStatus.SUCCESS)
    }

    @Test
    fun `deleteRecordFirebase api error`() {
        viewModel.deleteRecordFirebase("test")

        val value = viewModel.recordStatus.getOrAwaitValueTest()

        assertEquals(value.getContentIfNotHandled()?.status, ResStatus.ERROR)
    }

    @Test
    fun `deleteRecordFirebase api success`() {
        fakeScoreRepository.setShouldReturnNetworkError(false)

        viewModel.deleteRecordFirebase("test")

        val value = viewModel.recordStatus.getOrAwaitValueTest()

        assertEquals(value.getContentIfNotHandled()?.status, ResStatus.SUCCESS)
    }
}