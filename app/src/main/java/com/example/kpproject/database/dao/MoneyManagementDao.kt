package com.example.kpproject.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.kpproject.database.entity.MoneyManagement
import kotlinx.coroutines.flow.Flow

@Dao
interface MoneyManagementDao {
    @Insert
    suspend fun insertTransaksi(keuangan: MoneyManagement)
    @Update
    suspend fun updateTransaksi(keuangan: MoneyManagement)
    @Delete
    suspend fun deleteTransaksi(keuangan: MoneyManagement)
    @Query("SELECT * FROM money_management ORDER BY tanggal DESC, waktu DESC")
    fun getAllTransaksi(): Flow<List<MoneyManagement>>
    @Query("SELECT * FROM money_management WHERE tipe_transaksi = :tipe ORDER BY tanggal DESC, waktu DESC")
    fun getTransaksiByTipe(tipe: String): Flow<List<MoneyManagement>>
    @Query("SELECT SUM(nominal) FROM money_management WHERE tipe_transaksi = 'Pemasukan'")
    fun getTotalPemasukan(): Flow<Double?>
    @Query("SELECT SUM(nominal) FROM money_management WHERE tipe_transaksi = 'Pengeluaran'")
    fun getTotalPengeluaran(): Flow<Double?>
}