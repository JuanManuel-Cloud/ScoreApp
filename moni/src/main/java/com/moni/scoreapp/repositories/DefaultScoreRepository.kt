package com.moni.scoreapp.repositories

import com.moni.scoreapp.data.local.daos.ScoreDao
import com.moni.scoreapp.data.remote.MoniAPI
import com.moni.scoreapp.data.remote.MoniFirebaseAPI
import javax.inject.Inject

class DefaultScoreRepository @Inject constructor(
    private val shoppingDao: ScoreDao,
    private val moniAPI: MoniAPI,
    private val moniFirebaseAPI: MoniFirebaseAPI
) : ScoreRepository {

}