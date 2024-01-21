package com.moni.scoreapp.repositories

import android.util.Log
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
import com.moni.scoreapp.utils.ResStatus

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

    override suspend fun deleteRecord(id: String) {
        scoreDao.deleteRecord(id)
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

    override suspend fun getRecordsFirebase(): Resource<RecordListRs> {
        try {
            val response = moniFirebaseAPI.getRecordsFirebase()
            val res = checkResponse(response)
            if (res.status == ResStatus.ERROR) return res
            res.data as RecordListRs

            val records = res.data.documents.map {
                val fields = it.fields
                RecordItem(
                    id = it.getIdFromName(),
                    name = fields.nombre.stringValue,
                    lastname = fields.apellido.stringValue,
                    dni = fields.dni.stringValue,
                    email = fields.email.stringValue,
                    gender = fields.genero.stringValue,
                    status = fields.status.stringValue,
                    createdDate = it.createTime
                )
            }
            scoreDao.insertManyRecords(records)
            return res
        } catch (e: Exception) {
            return Resource.error("Couldn't reach the server. Check your internet connection", null)
        }
    }

    override suspend fun createRecordFirebase(recordRq: RecordRq): Resource<RecordRs> {
        try {
            val response = moniFirebaseAPI.createRecordFirebase(recordRq)
            val res = checkResponse(response)
            if (res.status == ResStatus.ERROR) return res
            val record = res.data as RecordRs
            val fields = record.fields

            scoreDao.insertRecord(
                RecordItem(
                    id = record.getIdFromName(),
                    name = fields.nombre.stringValue,
                    lastname = fields.apellido.stringValue,
                    dni = fields.dni.stringValue,
                    email = fields.email.stringValue,
                    gender = fields.genero.stringValue,
                    status = fields.status.stringValue,
                    createdDate = record.createTime
                )
            )
            return res
        } catch (e: Exception) {
            return Resource.error("Couldn't reach the server. Check your internet connection", null)
        }
    }

    override suspend fun deleteRecordFirebase(id: String): Resource<Unit> {
        try {
            val response = moniFirebaseAPI.deleteRecordFirebase(id)
            val res = checkResponse(response)
            if (res.status == ResStatus.ERROR) return res
            deleteRecord(id)
            return res
        } catch (e: Exception) {
            return Resource.error("Couldn't reach the server. Check your internet connection", null)
        }
    }
}