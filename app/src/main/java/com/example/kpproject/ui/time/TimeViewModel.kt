package com.example.kpproject.ui.time

import androidx.lifecycle.*
import com.example.kpproject.database.entity.TimeManagement
import com.example.kpproject.repository.TimeManagementRepository

class TimeViewModel(private val repository: TimeManagementRepository) : ViewModel() {

    private val _currentDay = MutableLiveData<String>()

    fun setDay(day: String) {
        _currentDay.value = day
    }

    val tasksPentingMendesak: LiveData<List<TimeManagement>> = _currentDay.switchMap { day ->
        repository.allTugas.asLiveData().map { list ->
            list.filter { it.hariMulai.equals(day, ignoreCase = true) && it.kategori == "Penting & Mendesak" }
        }
    }

    val tasksPentingTidakMendesak: LiveData<List<TimeManagement>> = _currentDay.switchMap { day ->
        repository.allTugas.asLiveData().map { list ->
            list.filter { it.hariMulai.equals(day, ignoreCase = true) && it.kategori == "Penting & Tidak Mendesak" }
        }
    }

    val tasksTidakPentingMendesak: LiveData<List<TimeManagement>> = _currentDay.switchMap { day ->
        repository.allTugas.asLiveData().map { list ->
            list.filter { it.hariMulai.equals(day, ignoreCase = true) && it.kategori == "Tidak Penting & Mendesak" }
        }
    }

    val tasksTidakPentingTidakMendesak: LiveData<List<TimeManagement>> = _currentDay.switchMap { day ->
        repository.allTugas.asLiveData().map { list ->
            list.filter { it.hariMulai.equals(day, ignoreCase = true) && it.kategori == "Tidak Penting & Tidak Mendesak" }
        }
    }
}
