package com.example.kpproject.ui.time

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kpproject.database.entity.TimeManagement
import com.example.kpproject.databinding.ItemDaiilyTaskBinding

class DailyTaskAdapter(private var taskList: List<TimeManagement>) :
    RecyclerView.Adapter<DailyTaskAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemDaiilyTaskBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(task: TimeManagement) {
            binding.tvNamaTugas.text = task.namaTugas
            binding.tvHariMulai.text = task.hariMulai
            binding.tvTanggalMulai.text = task.tanggalMulai
            binding.tvJamMulai.text = task.jamMulai
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemDaiilyTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(taskList[position])
    }

    override fun getItemCount() = taskList.size

    fun updateTasks(newTasks: List<TimeManagement>) {
        taskList = newTasks
        notifyDataSetChanged()
    }
}
