package com.moni.scoreapp.data.local.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface ScoreDao {
    @Insert
    suspend fun insertRecord(recordItem: RecordItem)

    @Insert
    suspend fun insertManyRecords(recordItems: List<RecordItem>)

    @Delete
    suspend fun deleteRecord(recordItem: RecordItem)

    @Query("SELECT * FROM record_item")
    fun getAllRecords(): LiveData<List<RecordItem>>

    /**
     * Find records by name, lastname or dni
     */
    @Query("SELECT * FROM record_item WHERE dni LIKE :query OR name LIKE :query OR lastname LIKE :query")
    fun findRecords(query: String): LiveData<List<RecordItem>>
}