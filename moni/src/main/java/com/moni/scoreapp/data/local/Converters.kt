package com.moni.scoreapp.data.local

import androidx.room.TypeConverter
import java.time.Instant

object Converters {
    @TypeConverter
    fun toInstant(epochSec: Long?): Instant? = epochSec?.let {
        Instant.ofEpochSecond(epochSec)
    }

    @TypeConverter
    fun fromInstant(instant: Instant?): Long? = instant?.epochSecond
}
