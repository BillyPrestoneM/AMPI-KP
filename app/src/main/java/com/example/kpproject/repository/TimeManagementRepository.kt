package com.example.kpproject.repository

import com.example.kpproject.database.dao.TimeManagementDao
import com.example.kpproject.database.entity.TimeManagement
import kotlinx.coroutines.flow.Flow

class TimeManagementRepository(private val timeManagementDao: TimeManagementDao) {

    val allTugas: Flow<List<TimeManagement>> = timeManagementDao.getAllTugas()
    suspend fun insertTugas(tugas: TimeManagement) {
        timeManagementDao.insertTugas(tugas)
    }
    suspend fun updateTugas(tugas: TimeManagement) {
        timeManagementDao.updateTugas(tugas)
    }
    suspend fun deleteTugas(tugas: TimeManagement) {
        timeManagementDao.deleteTugas(tugas)
    }
    fun getTugasByStatus(selesai: Boolean): Flow<List<TimeManagement>> {
        return timeManagementDao.getTugasByStatus(selesai)
    }
    fun getTugasByKategori(kategori: String): Flow<List<TimeManagement>> {
        return timeManagementDao.getTugasByKategori(kategori)
    }
}