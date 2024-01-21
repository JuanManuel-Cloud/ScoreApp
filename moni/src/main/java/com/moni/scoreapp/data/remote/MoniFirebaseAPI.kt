package com.moni.scoreapp.data.remote

import com.moni.scoreapp.data.remote.models.RecordListRs
import com.moni.scoreapp.data.remote.models.RecordRq
import com.moni.scoreapp.data.remote.models.RecordRs
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST

interface MoniFirebaseAPI {
    @GET
    suspend fun getRecordsFirebase(): Response<RecordListRs>

    @POST
    suspend fun createRecordFirebase(req: RecordRq): Response<RecordRs>

    @DELETE("{id}")
    suspend fun deleteRecordFirebase(id: String): Response<Unit>
}