package com.moni.scoreapp.data.local.daos

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.moni.scoreapp.data.local.enums.Genders
import com.moni.scoreapp.data.local.enums.RecordStatus
import java.time.Instant
import java.time.ZonedDateTime

@Entity(tableName = "record_item")
data class RecordItem(
    @PrimaryKey
    val id: String,
    val name: String,
    val lastname: String,
    val dni: String,
    val email: String,
    val gender: Genders,
    val status: RecordStatus,
    val createdDate: Instant
)
