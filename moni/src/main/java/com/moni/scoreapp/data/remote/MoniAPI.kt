package com.moni.scoreapp.data.remote

import com.moni.scoreapp.data.remote.models.RecordRs
import com.moni.scoreapp.data.remote.models.ScoreRs
import com.moni.scoreapp.utils.Constants.MONI_API_CREDENTIAL
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface MoniAPI {
    @GET("/v4/scoring/pre-score/{dni}")
    suspend fun getScore(
        @Header("credential") credential: String = MONI_API_CREDENTIAL,
        @Path("dni") dni: String
    ): Response<ScoreRs>
}