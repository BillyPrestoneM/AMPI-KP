package com.example.kpproject.repository

import com.example.kpproject.database.dao.MoneyManagementDao
import com.example.kpproject.database.entity.MoneyManagement
import kotlinx.coroutines.flow.Flow

class MoneyManagementRepository(private val moneyManagementDao: MoneyManagementDao) {

    // Mendapatkan semua transaksi keuangan dalam bentuk Flow
    val allTransaksi: Flow<List<MoneyManagement>> = moneyManagementDao.getAllTransaksi()

    // Mendapatkan total pemasukan dalam bentuk Flow
    val totalPemasukan: Flow<Double?> = moneyManagementDao.getTotalPemasukan()

    // Mendapatkan total pengeluaran dalam bentuk Flow
    val totalPengeluaran: Flow<Double?> = moneyManagementDao.getTotalPengeluaran()

    /**
     * Memasukkan transaksi keuangan baru ke database.
     * @param keuangan Objek MoneyManagement yang akan dimasukkan.
     */
    suspend fun insertTransaksi(keuangan: MoneyManagement) {
        moneyManagementDao.insertTransaksi(keuangan)
    }

    /**
     * Memperbarui transaksi keuangan yang sudah ada di database.
     * @param keuangan Objek MoneyManagement yang akan diperbarui.
     */
    suspend fun updateTransaksi(keuangan: MoneyManagement) {
        moneyManagementDao.updateTransaksi(keuangan)
    }

    /**
     * Menghapus transaksi keuangan dari database.
     * @param keuangan Objek MoneyManagement yang akan dihapus.
     */
    suspend fun deleteTransaksi(keuangan: MoneyManagement) {
        moneyManagementDao.deleteTransaksi(keuangan)
    }

    /**
     * Mendapatkan transaksi berdasarkan tipe (Pemasukan/Pengeluaran).
     * @param tipe Tipe transaksi yang akan difilter.
     * @return Flow dari daftar MoneyManagement yang sesuai dengan tipe.
     */
    fun getTransaksiByTipe(tipe: String): Flow<List<MoneyManagement>> {
        return moneyManagementDao.getTransaksiByTipe(tipe)
    }
}