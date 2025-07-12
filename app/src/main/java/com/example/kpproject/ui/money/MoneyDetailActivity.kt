package com.example.kpproject.ui.money

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.kpproject.databinding.ActivityMoneyDetailBinding
import com.example.kpproject.database.entity.MoneyManagement
import com.example.kpproject.repository.MoneyManagementRepository
import com.example.kpproject.database.roomdatabase.AppDatabase

class MoneyDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMoneyDetailBinding
    private lateinit var currentData: MoneyManagement
    private lateinit var repository: MoneyManagementRepository
    private lateinit var viewModel: MoneyDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMoneyDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dao = AppDatabase.getDatabase(this).moneyManagementDao()
        repository = MoneyManagementRepository(dao)
        val factory = MoneyDetailViewModelFactory(repository)
        viewModel = factory.create(MoneyDetailViewModel::class.java)

        // Ambil data dari intent
        currentData = intent.getParcelableExtra("money_data") ?: return

        // Tampilkan data ke UI
        binding.tvTransactionType.text = currentData.transactionType
        binding.tvAccountType.text = currentData.akun
        binding.tvCategory.text = currentData.kategori
        binding.tvDay.text = currentData.hari
        binding.tvTime.text = currentData.time
        binding.tvDate.text = currentData.date
        binding.tvNominal.text = "Rp ${currentData.nominal}"
        binding.tvDescription.text = currentData.deskripsi

        // Tombol Update
//        binding.btnUpdate.setOnClickListener {
//            val intent = Intent(this, UpdateMoneyActivity::class.java)
//            intent.putExtra("money_data", currentData)
//            startActivity(intent)
//        }

        // Tombol Delete
        binding.btnDelete.setOnClickListener {
            viewModel.deleteTransaksi(currentData)
            Toast.makeText(this, "Data berhasil dihapus", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}
