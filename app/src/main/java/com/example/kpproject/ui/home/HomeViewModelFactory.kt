package com.example.kpproject.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.kpproject.repository.MoneyManagementRepository
import com.example.kpproject.repository.TimeManagementRepository

class HomeViewModelFactory(
    private val moneyRepo: MoneyManagementRepository,
    private val timeRepo: TimeManagementRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(moneyRepo, timeRepo) as T
    }
}
