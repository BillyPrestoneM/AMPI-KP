package com.example.kpproject.ui.time

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.kpproject.repository.TimeManagementRepository

class AddTimeManagementViewModelFactory(private val repository: TimeManagementRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddTimeManagementViewModel::class.java)) {
            return AddTimeManagementViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
