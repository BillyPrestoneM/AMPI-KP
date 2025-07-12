package com.example.kpproject.ui.money

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.kpproject.database.entity.MoneyManagement
import com.example.kpproject.databinding.DailyTransactionBinding

class MoneyAdapter (
    private val onItemClick: (MoneyManagement) -> Unit) :
    ListAdapter<MoneyManagement, MoneyAdapter.MoneyViewHolder>(DiffCallback()) {

    inner class MoneyViewHolder(private val binding: DailyTransactionBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: MoneyManagement) = with(binding) {
            // Set data ke TextView
            tvTransactionType.text = item.transactionType
            tvTransactionCategory.text = item.kategori
            tvTransactionAccount.text = item.akun

            // Format nominal dan beri warna
            val nominalText = "Rp ${item.nominal}"
            tvTransactionAmount.text = nominalText

            tvTransactionAmount.setTextColor(
                when (item.transactionType) {
                    "Expenses" -> Color.RED
                    "Income" -> Color.parseColor("#008000")
                    else -> Color.BLACK
                }
            )
            root.setOnClickListener {
                onItemClick(item)
            }

            Log.d("MoneyAdapter", "Type: ${item.transactionType}, Nominal: ${item.nominal}")



        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoneyViewHolder {
        val binding = DailyTransactionBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MoneyViewHolder(binding)
    }
    override fun onBindViewHolder(holder: MoneyViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
    class DiffCallback : DiffUtil.ItemCallback<MoneyManagement>() {
        override fun areItemsTheSame(oldItem: MoneyManagement, newItem: MoneyManagement): Boolean {
            return oldItem.id == newItem.id
        }
        override fun areContentsTheSame(oldItem: MoneyManagement, newItem: MoneyManagement): Boolean {
            return oldItem == newItem
        }
    }
}
