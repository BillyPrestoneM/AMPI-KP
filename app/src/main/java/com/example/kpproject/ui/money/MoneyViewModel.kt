package com.example.kpproject.ui.money

import androidx.lifecycle.*
import com.example.kpproject.database.dao.MoneyManagementDao
import com.example.kpproject.database.entity.MoneyManagement
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MoneyViewModel(private val dao: MoneyManagementDao) : ViewModel() {

    private val _allTransactions = MutableLiveData<List<MoneyManagement>>()
    val allTransactions: LiveData<List<MoneyManagement>> = _allTransactions

    init {
        viewModelScope.launch(Dispatchers.IO) {
            dao.getAllTransaksi().collectLatest {
                _allTransactions.postValue(it)
            }
        }
    }
}


