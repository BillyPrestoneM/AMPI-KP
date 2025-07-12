package com.example.kpproject.ui.time

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.kpproject.databinding.ActivityDetailTimeBinding
import com.example.kpproject.database.entity.TimeManagement

class DetailTimeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailTimeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailTimeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val taskData = intent.getParcelableExtra<TimeManagement>("task_data")
        taskData?.let {
            binding.tvTaskName.text = it.namaTugas
            binding.tvTimeCategory.text = it.kategori
            binding.tvTaskDay.text = it.hariMulai
            binding.tvTaskDate.text = it.tanggalMulai
            binding.tvTaskTime.text = it.jamMulai
            binding.tvTaskDescription.text = it.deskripsiTugas
        }
    }
}
