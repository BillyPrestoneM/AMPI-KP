package com.example.kpproject.ui.money

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.kpproject.repository.MoneyManagementRepository

class MoneyDetailViewModelFactory(
    private val repository: MoneyManagementRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MoneyDetailViewModel::class.java)) {
            return MoneyDetailViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
