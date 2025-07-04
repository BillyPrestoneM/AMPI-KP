package com.example.kpproject.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.kpproject.database.entity.MoneyManagement
import com.example.kpproject.database.entity.TimeManagement
import com.example.kpproject.database.roomdatabase.AppDatabase
import com.example.kpproject.repository.MoneyManagementRepository
import com.example.kpproject.repository.TimeManagementRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    // Inisialisasi DAO dan Repository
    private val moneyManagementDao = AppDatabase.getDatabase(application).moneyManagementDao()
    private val timeManagementDao = AppDatabase.getDatabase(application).timeManagementDao()

    private val moneyManagementRepository: MoneyManagementRepository = MoneyManagementRepository(moneyManagementDao)
    private val timeManagementRepository: TimeManagementRepository = TimeManagementRepository(timeManagementDao)

    // --- LiveData untuk MoneyManagement ---

    // Semua transaksi keuangan, diobservasi sebagai LiveData
    val allTransaksi: LiveData<List<MoneyManagement>> = moneyManagementRepository.allTransaksi.asLiveData()

    // Total pemasukan, diobservasi sebagai LiveData
    val totalPemasukan: LiveData<Double?> = moneyManagementRepository.totalPemasukan.asLiveData()

    // Total pengeluaran, diobservasi sebagai LiveData
    val totalPengeluaran: LiveData<Double?> = moneyManagementRepository.totalPengeluaran.asLiveData()

    /**
     * Mendapatkan transaksi berdasarkan tipe (Pemasukan/Pengeluaran).
     * @param tipe Tipe transaksi yang akan difilter.
     * @return LiveData dari daftar MoneyManagement yang sesuai dengan tipe.
     */
    fun getTransaksiByTipe(tipe: String): LiveData<List<MoneyManagement>> {
        return moneyManagementRepository.getTransaksiByTipe(tipe).asLiveData()
    }

    /**
     * Memasukkan transaksi keuangan baru.
     * Operasi dilakukan di ViewModelScope pada Dispatchers.IO.
     * @param keuangan Objek MoneyManagement yang akan dimasukkan.
     */
    fun insertTransaksi(
        keuangan: MoneyManagement) = viewModelScope.launch(Dispatchers.IO) {
        moneyManagementRepository.insertTransaksi(keuangan)
    }

    /**
     * Memperbarui transaksi keuangan yang sudah ada.
     * Operasi dilakukan di ViewModelScope pada Dispatchers.IO.
     * @param keuangan Objek MoneyManagement yang akan diperbarui.
     */
    fun updateTransaksi(keuangan: MoneyManagement) = viewModelScope.launch(Dispatchers.IO) {
        moneyManagementRepository.updateTransaksi(keuangan)
    }

    /**
     * Menghapus transaksi keuangan.
     * Operasi dilakukan di ViewModelScope pada Dispatchers.IO.
     * @param keuangan Objek MoneyManagement yang akan dihapus.
     */
    fun deleteTransaksi(keuangan: MoneyManagement) = viewModelScope.launch(Dispatchers.IO) {
        moneyManagementRepository.deleteTransaksi(keuangan)
    }

    // --- LiveData untuk TimeManagement ---

    // Semua tugas, diobservasi sebagai LiveData
    val allTugas: LiveData<List<TimeManagement>> = timeManagementRepository.allTugas.asLiveData()

    /**
     * Mendapatkan tugas berdasarkan status (selesai/belum selesai).
     * @param selesai Status tugas yang akan difilter.
     * @return LiveData dari daftar TimeManagement yang sesuai dengan status.
     */
    fun getTugasByStatus(selesai: Boolean): LiveData<List<TimeManagement>> {
        return timeManagementRepository.getTugasByStatus(selesai).asLiveData()
    }

    /**
     * Mendapatkan tugas berdasarkan kategori.
     * @param kategori Kategori tugas yang akan difilter.
     * @return LiveData dari daftar TimeManagement yang sesuai dengan kategori.
     */
    fun getTugasByKategori(kategori: String): LiveData<List<TimeManagement>> {
        return timeManagementRepository.getTugasByKategori(kategori).asLiveData()
    }

    /**
     * Memasukkan tugas baru.
     * Operasi dilakukan di ViewModelScope pada Dispatchers.IO.
     * @param tugas Objek TimeManagement yang akan dimasukkan.
     */
    fun insertTugas(tugas: TimeManagement) = viewModelScope.launch(Dispatchers.IO) {
        timeManagementRepository.insertTugas(tugas)
    }

    /**
     * Memperbarui tugas yang sudah ada.
     * Operasi dilakukan di ViewModelScope pada Dispatchers.IO.
     * @param tugas Objek TimeManagement yang akan diperbarui.
     */
    fun updateTugas(tugas: TimeManagement) = viewModelScope.launch(Dispatchers.IO) {
        timeManagementRepository.updateTugas(tugas)
    }

    /**
     * Menghapus tugas.
     * Operasi dilakukan di ViewModelScope pada Dispatchers.IO.
     * @param tugas Objek TimeManagement yang akan dihapus.
     */
    fun deleteTugas(tugas: TimeManagement) = viewModelScope.launch(Dispatchers.IO) {
        timeManagementRepository.deleteTugas(tugas)
    }
}