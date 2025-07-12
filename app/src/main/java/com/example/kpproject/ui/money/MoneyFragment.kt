package com.example.kpproject.ui.money

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kpproject.database.roomdatabase.AppDatabase
import com.example.kpproject.databinding.FragmentMoneyBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MoneyFragment : Fragment() {

    private var _binding: FragmentMoneyBinding? = null
    private val binding get() = _binding!!

    private lateinit var moneyAdapter: MoneyAdapter
    private lateinit var moneyViewModel: MoneyViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoneyBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Inisialisasi Database & ViewModel
        val dao = AppDatabase.getDatabase(requireContext()).moneyManagementDao()
        moneyViewModel = ViewModelProvider(
            this,
            MoneyViewModelFactory(dao)
        )[MoneyViewModel::class.java]

        setupRecyclerView()
        setCurrentMonth()

        // Observe data
        moneyViewModel.allTransactions.observe(viewLifecycleOwner) { transactions ->
            moneyAdapter.submitList(transactions)
        }

        return root
    }
    private fun setupRecyclerView() {
        moneyAdapter = MoneyAdapter{
            selectedItem -> val intent = Intent(requireContext(), MoneyDetailActivity::class.java)
            intent.putExtra("money_data", selectedItem)
            startActivity(intent)
        }
        binding.rvLastTransaction.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = moneyAdapter
        }
    }
    private fun setCurrentMonth() {
        val calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("MMMM", Locale.getDefault())
        val currentMonth = dateFormat.format(calendar.time)

        binding.tvMonth.text = currentMonth
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
