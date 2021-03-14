package com.example.quiz

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.view.WindowManager
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import com.example.quiz.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


//        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        binding.btnStart.setOnClickListener {
            if(binding.etName.text.toString().isEmpty()){
                Toast.makeText(this, "Please enter your name", Toast.LENGTH_SHORT).show()
            }else{
                val intent = Intent(this, QuizQuestionsActivity::class.java)
                intent.putExtra(Constants.USER_NAME, binding.etName.text.toString())
                startActivity(intent)
                finish()
            }
        }
    }
}