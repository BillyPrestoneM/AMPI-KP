package com.example.kpproject.ui.money

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kpproject.database.entity.MoneyManagement
import com.example.kpproject.database.roomdatabase.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddMoneyManagementViewModel(private val database: AppDatabase) : ViewModel() {

    fun insertMoneyManagement(moneyManagement: MoneyManagement) {
        viewModelScope.launch(Dispatchers.IO) {
            database.moneyManagementDao().insertTransaksi(moneyManagement)
        }
    }
}