package com.example.a7minuteworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.a7minuteworkout.databinding.ActivityBMIBinding

class BMIActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBMIBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBMIBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbarBmiActivity)

        val actionbar = supportActionBar
        actionbar?.setDisplayHomeAsUpEnabled(true)
        actionbar?.title = "CALCULATE BMI"

        binding.toolbarBmiActivity.setNavigationOnClickListener {
            onBackPressed()
        }
    }
}