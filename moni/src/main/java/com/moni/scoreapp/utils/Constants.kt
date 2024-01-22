package com.moni.scoreapp.utils

import com.moni.scoreapp.BuildConfig

object Constants {
    const val DATABASE_NAME = "score_db"
    const val BASE_URL = "https://api.moni.com.ar/"
    const val BASE_FIREBASE_URL = "https://firestore.googleapis.com/"
    const val MONI_API_CREDENTIAL: String = BuildConfig.MONI_API_CREDENTIAL
    const val TIMEOUT = 30L
    const val MIN_QUERY_LEN = 4
}