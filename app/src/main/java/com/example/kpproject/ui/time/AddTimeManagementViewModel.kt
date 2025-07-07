package com.example.kpproject.ui.time

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kpproject.database.entity.TimeManagement
import com.example.kpproject.repository.TimeManagementRepository
import kotlinx.coroutines.launch

class AddTimeManagementViewModel(private val repository: TimeManagementRepository) : ViewModel() {

    fun insertTugas(tugas: TimeManagement) {
        viewModelScope.launch {
            repository.insertTugas(tugas)
        }
    }
}
