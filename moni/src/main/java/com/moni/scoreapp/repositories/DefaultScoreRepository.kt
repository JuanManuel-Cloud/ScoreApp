package com.moni.scoreapp.repositories

import androidx.lifecycle.LiveData
import com.moni.scoreapp.data.local.daos.RecordItem
import com.moni.scoreapp.data.local.daos.ScoreDao
import com.moni.scoreapp.data.remote.MoniAPI
import com.moni.scoreapp.data.remote.MoniFirebaseAPI
import com.moni.scoreapp.data.remote.models.RecordListRs
import com.moni.scoreapp.data.remote.models.RecordRq
import com.moni.scoreapp.data.remote.models.RecordRs
import com.moni.scoreapp.data.remote.models.ScoreRs
import com.moni.scoreapp.utils.NetworkUtils.checkResponse
import com.moni.scoreapp.utils.Resource
import javax.inject.Inject

class DefaultScoreRepository @Inject constructor(
    private val scoreDao: ScoreDao,
    private val moniAPI: MoniAPI,
    private val moniFirebaseAPI: MoniFirebaseAPI
) : ScoreRepository {
    override suspend fun insertRecord(recordItem: RecordItem) {
        scoreDao.insertRecord(recordItem)
    }

    override suspend fun insertManyRecords(recordItems: List<RecordItem>) {
        scoreDao.insertManyRecords(recordItems)
    }

    override suspend fun deleteRecord(recordItem: RecordItem) {
        scoreDao.deleteRecord(recordItem)
    }

    override fun getAllRecords(): LiveData<List<RecordItem>> {
        return scoreDao.getAllRecords()
    }

    override fun findRecords(query: String): LiveData<List<RecordItem>> {
        return scoreDao.findRecords(query)
    }

    override suspend fun getScore(dni: String): Resource<ScoreRs> = try {
        val response = moniAPI.getScore(dni = dni)
        checkResponse(response)
    } catch (e: Exception) {
        Resource.error("Couldn't reach the server. Check your internet connection", null)
    }

    override suspend fun getRecordsFirebase(): Resource<RecordListRs> = try {
        val response = moniFirebaseAPI.getRecordsFirebase()
        checkResponse(response)
    } catch (e: Exception) {
        Resource.error("Couldn't reach the server. Check your internet connection", null)
    }

    override suspend fun createRecordFirebase(recordItem: RecordItem): Resource<RecordRs> = try {
        val response = moniFirebaseAPI.createRecordFirebase(RecordRq(recordItem))
        checkResponse(response)
    } catch (e: Exception) {
        Resource.error("Couldn't reach the server. Check your internet connection", null)
    }

    override suspend fun deleteRecordFirebase(id: String): Resource<Unit> = try {
        val response = moniFirebaseAPI.deleteRecordFirebase(id)
        checkResponse(response)
    } catch (e: Exception) {
        Resource.error("Couldn't reach the server. Check your internet connection", null)
    }
}