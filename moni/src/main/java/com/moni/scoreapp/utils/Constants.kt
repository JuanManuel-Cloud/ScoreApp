package com.moni.scoreapp.utils

import com.moni.scoreapp.BuildConfig

object Constants {
    const val DATABASE_NAME = "score_db"
    const val BASE_URL = "https://api.moni.com.ar/api/"
    const val BASE_FIREBASE_URL =
        "https://firestore.googleapis.com/v1/projects/wired-torus-98413/databases/(default)/documents/users/"
    const val MONI_API_CREDENTIAL: String = BuildConfig.MONI_API_CREDENTIAL
}