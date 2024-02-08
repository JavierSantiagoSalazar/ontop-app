package com.example.ontopapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.ontopapp.data.database.localmodel.DbMovie

@Database(entities = [DbMovie::class], version = 1, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao
}
