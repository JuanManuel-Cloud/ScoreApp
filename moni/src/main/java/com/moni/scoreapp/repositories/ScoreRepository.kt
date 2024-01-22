package com.moni.scoreapp.repositories

import androidx.lifecycle.LiveData
import com.moni.scoreapp.data.local.daos.RecordItem
import com.moni.scoreapp.data.remote.models.RecordListRs
import com.moni.scoreapp.data.remote.models.RecordRq
import com.moni.scoreapp.data.remote.models.RecordRs
import com.moni.scoreapp.data.remote.models.ScoreRs
import com.moni.scoreapp.utils.Resource

interface ScoreRepository {
    suspend fun insertRecord(recordItem: RecordItem)
    suspend fun insertManyRecords(recordItems: List<RecordItem>)
    suspend fun deleteRecord(id: String)
    fun getAllRecords(): LiveData<List<RecordItem>>
    fun findRecords(query: String, all: Boolean = false): LiveData<List<RecordItem>>
    suspend fun getScore(dni: String): Resource<ScoreRs>
    suspend fun getRecordsFirebase(): Resource<RecordListRs>
    suspend fun createRecordFirebase(recordRq: RecordRq): Resource<RecordRs>
    suspend fun deleteRecordFirebase(id: String): Resource<Unit>
}