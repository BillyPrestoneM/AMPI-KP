package com.example.kpproject.ui.money

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.kpproject.database.dao.MoneyManagementDao

class MoneyViewModelFactory(
    private val dao: MoneyManagementDao
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MoneyViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MoneyViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}