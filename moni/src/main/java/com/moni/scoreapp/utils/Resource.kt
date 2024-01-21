package com.moni.scoreapp.utils

data class Resource<out T>(val status: ResStatus, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(ResStatus.SUCCESS, data, null)
        }

        fun <T> error(msg: String, data: T?): Resource<T> {
            return Resource(ResStatus.ERROR, data, msg)
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(ResStatus.LOADING, data, null)
        }
    }
}

enum class ResStatus {
    SUCCESS,
    ERROR,
    LOADING
}