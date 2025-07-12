package com.example.kpproject.repository

import com.example.kpproject.database.dao.MoneyManagementDao
import com.example.kpproject.database.entity.MoneyManagement
import kotlinx.coroutines.flow.Flow

class MoneyManagementRepository(private val moneyManagementDao: MoneyManagementDao) {

    val allTransaksi: Flow<List<MoneyManagement>> = moneyManagementDao.getAllTransaksi()
    val totalPemasukan: Flow<Double?> = moneyManagementDao.getTotalPemasukan()
    val totalPengeluaran: Flow<Double?> = moneyManagementDao.getTotalPengeluaran()
    suspend fun insertTransaksi(keuangan: MoneyManagement) {
        moneyManagementDao.insertTransaksi(keuangan)
    }
    suspend fun updateTransaksi(keuangan: MoneyManagement) {
        moneyManagementDao.updateTransaksi(keuangan)
    }
    suspend fun deleteTransaksi(keuangan: MoneyManagement) {
        moneyManagementDao.deleteTransaksi(keuangan)
    }
    fun getTransaksiByTipe(tipe: String): Flow<List<MoneyManagement>> {
        return moneyManagementDao.getTransaksiByTipe(tipe)
    }
}