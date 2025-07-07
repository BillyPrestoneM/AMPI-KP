package com.example.kpproject.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kpproject.database.roomdatabase.AppDatabase
import com.example.kpproject.databinding.FragmentHomeBinding
import com.example.kpproject.repository.MoneyManagementRepository
import com.example.kpproject.repository.TimeManagementRepository
import com.example.kpproject.ui.AddActivity
import com.example.kpproject.ui.money.MoneyAdapter
import com.example.kpproject.ui.time.DailyTaskAdapter
import java.text.SimpleDateFormat
import java.util.*

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var moneyAdapter: MoneyAdapter
    private lateinit var taskAdapter: DailyTaskAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        // Inisialisasi Room & Repository
        val daoMoney = AppDatabase.getDatabase(requireContext()).moneyManagementDao()
        val daoTime = AppDatabase.getDatabase(requireContext()).timeManagementDao()
        val moneyRepo = MoneyManagementRepository(daoMoney)
        val timeRepo = TimeManagementRepository(daoTime)

        // ViewModel pakai Factory
        val viewModelFactory = HomeViewModelFactory(moneyRepo, timeRepo)
        val viewModel = ViewModelProvider(this, viewModelFactory)[HomeViewModel::class.java]

        setupRecyclerViews()

        // Hari sekarang
        val currentDay = SimpleDateFormat("EEEE", Locale.getDefault()).format(Date())
        viewModel.setDay(currentDay)

        // Observe Transaksi
        viewModel.transactions.observe(viewLifecycleOwner) { list ->
            moneyAdapter.submitList(list)
        }

        // Observe Tugas
        viewModel.dailyTasks.observe(viewLifecycleOwner) { list ->
            taskAdapter.updateTasks(list)
        }

        // Button tambah
        binding.btnAdd.setOnClickListener {
            startActivity(Intent(requireContext(), AddActivity::class.java))
        }

        return binding.root
    }

    private fun setupRecyclerViews() {
        moneyAdapter = MoneyAdapter()
        binding.rvTransaction.layoutManager = LinearLayoutManager(requireContext())
        binding.rvTransaction.adapter = moneyAdapter

        taskAdapter = DailyTaskAdapter(emptyList())
        binding.rvTask.layoutManager = LinearLayoutManager(requireContext())
        binding.rvTask.adapter = taskAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
