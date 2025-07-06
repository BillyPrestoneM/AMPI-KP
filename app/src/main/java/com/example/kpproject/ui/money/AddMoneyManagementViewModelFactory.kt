package com.example.kpproject.ui.money

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.kpproject.database.roomdatabase.AppDatabase

class AddMoneyManagementViewModelFactory(private val database: AppDatabase) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddMoneyManagementViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AddMoneyManagementViewModel(database) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}