package com.example.kpproject.ui.time

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.kpproject.database.entity.TimeManagement
import com.example.kpproject.database.roomdatabase.AppDatabase
import com.example.kpproject.databinding.ActivityAddTimeManagementBinding
import com.example.kpproject.repository.TimeManagementRepository
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class AddTimeManagement : AppCompatActivity() {

    private lateinit var binding: ActivityAddTimeManagementBinding

    private val viewModel: AddTimeManagementViewModel by viewModels {
        AddTimeManagementViewModelFactory(TimeManagementRepository(AppDatabase.getDatabase(this).timeManagementDao()))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddTimeManagementBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupKategoriSpinner()
        setupHariSpinner()
        setupTanggalPicker()

        binding.btnSave.setOnClickListener {
            val namaTugas = binding.etNamaTugas.text.toString()
            val deskripsi = binding.etDeskripsiTugas.text.toString().ifEmpty { null }
            val kategori = binding.etKategori.text.toString()
            val hariMulai = binding.etHariMulai.text.toString()
            val tanggalMulai = binding.etTanggalMulai.text.toString()
            val jamMulai = binding.etJamMulai.text.toString()

            if (namaTugas.isBlank() || kategori.isBlank() || hariMulai.isBlank() || tanggalMulai.isBlank() || jamMulai.isBlank()) {
                Toast.makeText(this, "Lengkapi data terlebih dahulu!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val timeManagement = TimeManagement(
                namaTugas = namaTugas,
                deskripsiTugas = deskripsi,
                kategori = kategori,
                hariMulai = hariMulai,
                tanggalMulai = tanggalMulai,
                jamMulai = jamMulai,
                hariSelesai = null,
                tanggalSelesai = null,
                jamSelesai = null,
                status = false
            )

            viewModel.insertTugas(timeManagement)

            Toast.makeText(this, "Data berhasil disimpan", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    private fun setupKategoriSpinner() {
        val kategoriList = listOf(
            "Penting & Mendesak",
            "Penting & Tidak Mendesak",
            "Tidak Penting & Mendesak",
            "Tidak Penting & Tidak Mendesak"
        )
        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, kategoriList)
        binding.etKategori.setAdapter(adapter)
    }

    private fun setupHariSpinner() {
        val hariList = listOf("Senin", "Selasa", "Rabu", "Kamis", "Jumat", "Sabtu", "Minggu")
        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, hariList)
        binding.etHariMulai.setAdapter(adapter)
    }

    private fun setupTanggalPicker() {
        val calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

        binding.etTanggalMulai.setOnClickListener {
            DatePickerDialog(this,
                { _, year, month, dayOfMonth ->
                    calendar.set(Calendar.YEAR, year)
                    calendar.set(Calendar.MONTH, month)
                    calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                    binding.etTanggalMulai.setText(dateFormat.format(calendar.time))
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }
    }
}
