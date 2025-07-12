package com.example.kpproject.ui.time

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kpproject.database.entity.TimeManagement
import com.example.kpproject.database.roomdatabase.AppDatabase
import com.example.kpproject.databinding.DailyTaskBinding
import com.example.kpproject.databinding.FragmentTimeBinding
import com.example.kpproject.repository.TimeManagementRepository
import java.text.SimpleDateFormat
import java.util.*

class TimeFragment : Fragment() {

    private var _binding: FragmentTimeBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapterQuadrant1: DailyTaskAdapter
    private lateinit var adapterQuadrant2: DailyTaskAdapter
    private lateinit var adapterQuadrant3: DailyTaskAdapter
    private lateinit var adapterQuadrant4: DailyTaskAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentTimeBinding.inflate(inflater, container, false)

        val dao = AppDatabase.getDatabase(requireContext()).timeManagementDao()
        val repository = TimeManagementRepository(dao)
        val viewModelFactory = TimeViewModelFactory(repository)
        val timeViewModel = ViewModelProvider(this, viewModelFactory)[TimeViewModel::class.java]

        setupRecyclerViews(inflater)

        val currentDay = SimpleDateFormat("EEEE", Locale.getDefault()).format(Date())
        timeViewModel.setDay(currentDay)

        timeViewModel.tasksPentingMendesak.observe(viewLifecycleOwner) { tasks ->
            adapterQuadrant1.updateTasks(tasks)
        }

        timeViewModel.tasksPentingTidakMendesak.observe(viewLifecycleOwner) { tasks ->
            adapterQuadrant2.updateTasks(tasks)
        }

        timeViewModel.tasksTidakPentingMendesak.observe(viewLifecycleOwner) { tasks ->
            adapterQuadrant3.updateTasks(tasks)
        }

        timeViewModel.tasksTidakPentingTidakMendesak.observe(viewLifecycleOwner) { tasks ->
            adapterQuadrant4.updateTasks(tasks)
        }

        return binding.root
    }

    private fun openDetailTimeActivity(task: TimeManagement) {
        val intent = Intent(requireContext(), DetailTimeActivity::class.java)
        intent.putExtra("task_data", task)
        startActivity(intent)
    }


    private fun setupRecyclerViews(inflater: LayoutInflater) {
        val quadrant1Binding = DailyTaskBinding.inflate(inflater)
        adapterQuadrant1 = DailyTaskAdapter(emptyList()) { task ->
            openDetailTimeActivity(task)}
        quadrant1Binding.rvCategory.layoutManager = LinearLayoutManager(requireContext())
        quadrant1Binding.rvCategory.adapter = adapterQuadrant1
        quadrant1Binding.tvCategoryTitle.text = "Penting & Mendesak"
        binding.containerQuadrant1.addView(quadrant1Binding.root)

        val quadrant2Binding = DailyTaskBinding.inflate(inflater)
        adapterQuadrant2 = DailyTaskAdapter(emptyList()) { task ->
            openDetailTimeActivity(task)}
        quadrant2Binding.rvCategory.layoutManager = LinearLayoutManager(requireContext())
        quadrant2Binding.rvCategory.adapter = adapterQuadrant2
        quadrant2Binding.tvCategoryTitle.text = "Penting & Tidak Mendesak"
        binding.containerQuadrant2.addView(quadrant2Binding.root)

        val quadrant3Binding = DailyTaskBinding.inflate(inflater)
        adapterQuadrant3 = DailyTaskAdapter(emptyList()) { task ->
            openDetailTimeActivity(task)}
        quadrant3Binding.rvCategory.layoutManager = LinearLayoutManager(requireContext())
        quadrant3Binding.rvCategory.adapter = adapterQuadrant3
        quadrant3Binding.tvCategoryTitle.text = "Tidak Penting & Mendesak"
        binding.containerQuadrant3.addView(quadrant3Binding.root)

        val quadrant4Binding = DailyTaskBinding.inflate(inflater)
        adapterQuadrant4 = DailyTaskAdapter(emptyList()) { task ->
            openDetailTimeActivity(task)}
        quadrant4Binding.rvCategory.layoutManager = LinearLayoutManager(requireContext())
        quadrant4Binding.rvCategory.adapter = adapterQuadrant4
        quadrant4Binding.tvCategoryTitle.text = "Tidak Penting & Tidak Mendesak"
        binding.containerQuadrant4.addView(quadrant4Binding.root)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
