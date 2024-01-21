package com.moni.scoreapp.utils

import retrofit2.Response

object NetworkUtils {
    fun <T> checkResponse(response: Response<T>): Resource<T> {
        return if (response.isSuccessful) {
            response.body()?.let { Resource.success(it) }
                ?: Resource.error("An unknown error occurred", null)
        } else {
            Resource.error("An unknown error occurred", null)
        }
    }
}