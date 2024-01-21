package com.moni.scoreapp.data.local.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ScoreDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecord(recordItem: RecordItem)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertManyRecords(recordItems: List<RecordItem>)

    @Query("DELETE FROM record_item WHERE id = :id")
    suspend fun deleteRecord(id: String)

    @Query("SELECT * FROM record_item")
    fun getAllRecords(): LiveData<List<RecordItem>>

    /**
     * Find records by name, lastname or dni
     */
    @Query("SELECT * FROM record_item WHERE dni LIKE :query OR name LIKE :query OR lastname LIKE :query")
    fun findRecords(query: String): LiveData<List<RecordItem>>
}