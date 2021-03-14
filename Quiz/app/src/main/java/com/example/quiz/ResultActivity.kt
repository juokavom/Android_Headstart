package com.example.quiz

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.RequiresApi
import com.example.quiz.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN



        val username: String? = intent.getStringExtra(Constants.USER_NAME)
        binding.tvName.text = username

        val totalQuestions = intent.getIntExtra(Constants.TOTAL_QUESTIONS, 0)
        val correctAnswers = intent.getIntExtra(Constants.CORRECT_ANSWERS, 0)

        binding.tvScore.text = "Your Score is $correctAnswers out of $totalQuestions"

        binding.btnFinish.setOnClickListener{
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

}