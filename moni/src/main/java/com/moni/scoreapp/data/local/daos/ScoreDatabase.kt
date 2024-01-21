package com.moni.scoreapp.data.local.daos

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.moni.scoreapp.data.local.Converters

@Database(
    entities = [RecordItem::class],
    exportSchema = false,
    version = 2
)
@TypeConverters(Converters::class)
abstract class ScoreDatabase : RoomDatabase() {
    abstract fun scoreDao(): ScoreDao
}