package com.example.ontopapp.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.ontopapp.data.database.localmodel.DbMovie
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Query("SELECT * FROM DbMovie")
    fun getAll(): Flow<List<DbMovie>>

    @Query("SELECT * FROM DbMovie WHERE id = :id")
    fun findById(id: Int): Flow<DbMovie>

    @Query("SELECT COUNT(id) FROM DbMovie")
    suspend fun movieCount(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(dbMovies: List<DbMovie>)
}
