package com.example.kpproject.ui.home

import androidx.lifecycle.*
import com.example.kpproject.database.entity.MoneyManagement
import com.example.kpproject.database.entity.TimeManagement
import com.example.kpproject.repository.MoneyManagementRepository
import com.example.kpproject.repository.TimeManagementRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class HomeViewModel(
    private val moneyRepo: MoneyManagementRepository,
    private val timeRepo: TimeManagementRepository
) : ViewModel() {

    val transactions: LiveData<List<MoneyManagement>> = moneyRepo.allTransaksi.asLiveData()

    private val _currentDay = MutableLiveData<String>()

    fun setDay(day: String) {
        _currentDay.value = day
    }

    val dailyTasks: LiveData<List<TimeManagement>> = _currentDay.switchMap { day ->
        timeRepo.allTugas.asLiveData().map { list ->
            list.filter { it.hariMulai.equals(day, ignoreCase = true) }
        }
    }
}
