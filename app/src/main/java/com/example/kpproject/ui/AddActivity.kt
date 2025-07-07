package com.example.kpproject.ui

import com.example.kpproject.ui.money.AddMoneyManagement
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.kpproject.databinding.ActivityAddBinding
import com.example.kpproject.ui.time.AddTimeManagement

class AddActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Klik button "Create New Money Management"
        binding.btnMoneyManagement.setOnClickListener {
            val intent = Intent(this, AddMoneyManagement::class.java)
            startActivity(intent)
        }

        // Klik button "Create New Time Management"
        binding.btnTimeManagement.setOnClickListener {
            val intent = Intent(this, AddTimeManagement::class.java)
            startActivity(intent)
        }
    }
}
