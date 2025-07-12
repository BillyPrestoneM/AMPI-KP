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
import com.example.kpproject.ui.money.MoneyDetailActivity
import com.example.kpproject.ui.money.MoneyAdapter
import com.example.kpproject.ui.time.DailyTaskAdapter
import com.example.kpproject.ui.time.DetailTimeActivity
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
        val viewModelFactory = HomeViewModelFactory(moneyRepo, timeRepo)
        val viewModel = ViewModelProvider(this, viewModelFactory)[HomeViewModel::class.java]
        setupRecyclerViews()
        val currentDay = SimpleDateFormat("EEEE", Locale.getDefault()).format(Date())
        viewModel.setDay(currentDay)
        viewModel.transactions.observe(viewLifecycleOwner) { list ->
            moneyAdapter.submitList(list)
        }
        viewModel.dailyTasks.observe(viewLifecycleOwner) { list ->
            taskAdapter.updateTasks(list)
        }
        binding.btnAdd.setOnClickListener {
            startActivity(Intent(requireContext(), AddActivity::class.java))
        }
        return binding.root
    }
    private fun setupRecyclerViews() {
        moneyAdapter = MoneyAdapter{
            selectedItem -> val intent = Intent(requireContext(), MoneyDetailActivity::class.java)
            intent.putExtra("money_data", selectedItem)
            startActivity(intent)
        }
        binding.rvTransaction.layoutManager = LinearLayoutManager(requireContext())
        binding.rvTransaction.adapter = moneyAdapter

        taskAdapter = DailyTaskAdapter(emptyList()) {
            selectedItem -> val intent = Intent(requireContext(), DetailTimeActivity::class.java)
            intent.putExtra("task_data", selectedItem)
            startActivity(intent)
        }
        binding.rvTask.layoutManager = LinearLayoutManager(requireContext())
        binding.rvTask.adapter = taskAdapter
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
