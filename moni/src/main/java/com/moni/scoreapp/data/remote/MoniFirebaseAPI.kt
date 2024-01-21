package com.moni.scoreapp.data.remote

import com.moni.scoreapp.data.remote.models.RecordListRs
import com.moni.scoreapp.data.remote.models.RecordRq
import com.moni.scoreapp.data.remote.models.RecordRs
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST

interface MoniFirebaseAPI {
    @GET("/v1/projects/wired-torus-98413/databases/(default)/documents/users/")
    suspend fun getRecordsFirebase(): Response<RecordListRs>

    @POST("/v1/projects/wired-torus-98413/databases/(default)/documents/users/")
    suspend fun createRecordFirebase(@Body req: RecordRq): Response<RecordRs>

    @DELETE("/v1/projects/wired-torus-98413/databases/(default)/documents/users/{id}")
    suspend fun deleteRecordFirebase(id: String): Response<Unit>
}