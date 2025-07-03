package com.example.kpproject.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.kpproject.database.entity.TimeManagement
import kotlinx.coroutines.flow.Flow

@Dao
interface TimeManagementDao {

    @Insert
    suspend fun insertTugas(tugas: TimeManagement)

    @Update
    suspend fun updateTugas(tugas: TimeManagement)

    @Delete
    suspend fun deleteTugas(tugas: TimeManagement)

    @Query("SELECT * FROM time_management ORDER BY tanggal_mulai ASC, jam_mulai ASC")
    fun getAllTugas(): Flow<List<TimeManagement>>

    @Query("SELECT * FROM time_management WHERE status = :selesai ORDER BY tanggal_mulai ASC, jam_mulai ASC")
    fun getTugasByStatus(selesai: Boolean): Flow<List<TimeManagement>>

    @Query("SELECT * FROM time_management WHERE kategori = :kategori ORDER BY tanggal_mulai ASC, jam_mulai ASC")
    fun getTugasByKategori(kategori: String): Flow<List<TimeManagement>>
}