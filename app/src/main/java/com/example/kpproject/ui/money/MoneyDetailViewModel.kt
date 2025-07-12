package com.example.kpproject.ui.money

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kpproject.database.entity.MoneyManagement
import com.example.kpproject.repository.MoneyManagementRepository
import kotlinx.coroutines.launch

class MoneyDetailViewModel(private val repository: MoneyManagementRepository) : ViewModel() {

    fun deleteTransaksi(transaksi: MoneyManagement) {
        viewModelScope.launch {
            repository.deleteTransaksi(transaksi)
        }
    }

    fun updateTransaksi(transaksi: MoneyManagement) {
        viewModelScope.launch {
            repository.updateTransaksi(transaksi)
        }
    }
}
