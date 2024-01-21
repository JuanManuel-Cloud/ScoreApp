package com.moni.scoreapp.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.moni.scoreapp.data.local.daos.RecordItem
import com.moni.scoreapp.data.local.enums.Genders
import com.moni.scoreapp.data.local.enums.RecordStatus
import com.moni.scoreapp.data.remote.models.Apellido
import com.moni.scoreapp.data.remote.models.Dni
import com.moni.scoreapp.data.remote.models.Email
import com.moni.scoreapp.data.remote.models.Fields
import com.moni.scoreapp.data.remote.models.Genero
import com.moni.scoreapp.data.remote.models.Nombre
import com.moni.scoreapp.data.remote.models.RecordListRs
import com.moni.scoreapp.data.remote.models.RecordRq
import com.moni.scoreapp.data.remote.models.RecordRs
import com.moni.scoreapp.data.remote.models.ScoreRs
import com.moni.scoreapp.data.remote.models.Status
import com.moni.scoreapp.utils.Resource
import java.time.Instant

class FakeScoreRepository : ScoreRepository {
    private val user1: Fields
    private val name1: String

    private val recordItems = mutableListOf<RecordItem>()
    private val observableRecords = MutableLiveData<List<RecordItem>>(recordItems)

    private var shouldReturnNetworkError = false

    init {
        user1 = Fields(
            nombre = Nombre("Test"),
            apellido = Apellido("Test"),
            dni = Dni("11111111"),
            email = Email("test@test.com"),
            genero = Genero(Genders.MALE),
            status = Status(RecordStatus.REJECTED)
        )
        name1 =
            "projects/wired-torus-98413/databases/(default)/documents/users/CropRZlJTUpl33sFsgCp"
    }

    fun setShouldReturnNetworkError(value: Boolean) {
        shouldReturnNetworkError = value
    }

    private fun refreshLiveData() {
        observableRecords.postValue(recordItems)
    }

    override suspend fun insertRecord(recordItem: RecordItem) {
        recordItems.add(recordItem)
        refreshLiveData()
    }

    override suspend fun insertManyRecords(recordItems: List<RecordItem>) {
        this.recordItems.addAll(recordItems)
        refreshLiveData()
    }

    override suspend fun deleteRecord(id: String) {
        recordItems.removeIf { it.id == id }
        refreshLiveData()
    }

    override fun getAllRecords(): LiveData<List<RecordItem>> {
        return observableRecords
    }

    override fun findRecords(query: String): LiveData<List<RecordItem>> {
        return observableRecords
    }

    override suspend fun getScore(dni: String): Resource<ScoreRs> {
        if (shouldReturnNetworkError) {
            return Resource.error("Error", null)
        }
        return Resource.success(ScoreRs(false, RecordStatus.APPROVE))
    }

    override suspend fun getRecordsFirebase(): Resource<RecordListRs> {
        if (shouldReturnNetworkError) {
            return Resource.error("Error", null)
        }

        val user2 = Fields(
            nombre = Nombre("Test2"),
            apellido = Apellido("Test2"),
            dni = Dni("22222222"),
            email = Email("test2@test2.com"),
            genero = Genero(Genders.FEMALE),
            status = Status(RecordStatus.APPROVE)
        )

        val name2 =
            "projects/wired-torus-98413/databases/(default)/documents/users/CropRZlJTUpl33sAlkCp"

        return Resource.success(
            RecordListRs(
                listOf(
                    RecordRs(Instant.now(), user1, name1),
                    RecordRs(Instant.now(), user2, name2)
                )
            )
        )
    }

    override suspend fun createRecordFirebase(recordRq: RecordRq): Resource<RecordRs> {
        if (shouldReturnNetworkError) {
            return Resource.error("Error", null)
        }

        return Resource.success(
            RecordRs(Instant.now(), user1, name1),
        )
    }

    override suspend fun deleteRecordFirebase(id: String): Resource<Unit> {
        if (shouldReturnNetworkError) {
            return Resource.error("Error", null)
        }

        return Resource.success(Unit)
    }
}