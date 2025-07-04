package com.example.kpproject.repository

import com.example.kpproject.database.dao.TimeManagementDao
import com.example.kpproject.database.entity.TimeManagement
import kotlinx.coroutines.flow.Flow

class TimeManagementRepository(private val timeManagementDao: TimeManagementDao) {

    // Mendapatkan semua tugas dalam bentuk Flow
    val allTugas: Flow<List<TimeManagement>> = timeManagementDao.getAllTugas()

    /**
     * Memasukkan tugas baru ke database.
     * @param tugas Objek TimeManagement yang akan dimasukkan.
     */
    suspend fun insertTugas(tugas: TimeManagement) {
        timeManagementDao.insertTugas(tugas)
    }

    /**
     * Memperbarui tugas yang sudah ada di database.
     * @param tugas Objek TimeManagement yang akan diperbarui.
     */
    suspend fun updateTugas(tugas: TimeManagement) {
        timeManagementDao.updateTugas(tugas)
    }

    /**
     * Menghapus tugas dari database.
     * @param tugas Objek TimeManagement yang akan dihapus.
     */
    suspend fun deleteTugas(tugas: TimeManagement) {
        timeManagementDao.deleteTugas(tugas)
    }

    /**
     * Mendapatkan tugas berdasarkan status (selesai/belum selesai).
     * @param selesai Status tugas yang akan difilter (true untuk selesai, false untuk belum selesai).
     * @return Flow dari daftar TimeManagement yang sesuai dengan status.
     */
    fun getTugasByStatus(selesai: Boolean): Flow<List<TimeManagement>> {
        return timeManagementDao.getTugasByStatus(selesai)
    }

    /**
     * Mendapatkan tugas berdasarkan kategori.
     * @param kategori Kategori tugas yang akan difilter.
     * @return Flow dari daftar TimeManagement yang sesuai dengan kategori.
     */
    fun getTugasByKategori(kategori: String): Flow<List<TimeManagement>> {
        return timeManagementDao.getTugasByKategori(kategori)
    }
}