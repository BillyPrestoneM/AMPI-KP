package com.example.kpproject.ui.money

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.kpproject.database.entity.MoneyManagement
import com.example.kpproject.database.roomdatabase.AppDatabase
import com.example.kpproject.databinding.ActivityAddMoneyManagementBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class AddMoneyManagement : AppCompatActivity() {

    private lateinit var binding: ActivityAddMoneyManagementBinding
    private val viewModel: AddMoneyManagementViewModel by viewModels {
        AddMoneyManagementViewModelFactory(AppDatabase.getDatabase(this))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddMoneyManagementBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupDropdowns()
        setupDatePicker()
        setupTimePicker()
        setDefaultTime()

        binding.btnSave.setOnClickListener {
            saveData()
        }
    }

    private fun setupDropdowns() {
        val transactionTypes = listOf("Expenses", "Income")
        val accountTypes = listOf("Cash", "Bank Transfer", "Credit card", "E-Wallet")
        val days = listOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")

        binding.etTransactionType.setAdapter(ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, transactionTypes))
        binding.etAccountType.setAdapter(ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, accountTypes))
        binding.etDay.setAdapter(ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, days))
    }

    private fun setupDatePicker() {
        binding.etDate.setOnClickListener {
            val calendar = Calendar.getInstance()
            val datePicker = DatePickerDialog(this,
                { _, year, month, dayOfMonth ->
                    val date = String.format("%04d-%02d-%02d", year, month + 1, dayOfMonth)
                    binding.etDate.setText(date)
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            )
            datePicker.show()
        }
    }

    private fun setupTimePicker() {
        binding.etTime.setOnClickListener {
            val calendar = Calendar.getInstance()
            val timePicker = TimePickerDialog(this,
                { _, hourOfDay, minute ->
                    val time = String.format("%02d:%02d", hourOfDay, minute)
                    binding.etTime.setText(time)
                },
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                true
            )
            timePicker.show()
        }
    }

    private fun setDefaultTime() {
        val currentTime = SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date())
        binding.etTime.setText(currentTime)
    }

    private fun saveData() {
        val transactionType = binding.etTransactionType.text.toString()
        val accountType = binding.etAccountType.text.toString()
        val category = binding.etCategory.text.toString()
        val day = binding.etDay.text.toString()
        val time = binding.etTime.text.toString()
        val date = binding.etDate.text.toString()
        val description = binding.etDescription.text.toString()
        val nominal = binding.etNominal.text.toString().toDoubleOrNull() ?: 0.0

        if (transactionType.isBlank() || accountType.isBlank() || category.isBlank() || day.isBlank() || time.isBlank() || date.isBlank()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            return
        }

        val moneyManagement = MoneyManagement(
            transactionType = transactionType,
            akun = accountType,
            kategori = category,
            deskripsi = description,
            nominal = nominal,
            hari = day,
            date = date,
            time = time
        )

        viewModel.insertMoneyManagement(moneyManagement)

        Toast.makeText(this, "Data saved successfully", Toast.LENGTH_SHORT).show()
        finish()
    }
}
