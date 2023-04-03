package com.compose.mathsquiz.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.compose.mathsquiz.databinding.ActivityMainBinding
import com.compose.mathsquiz.domain.QuizType
import com.compose.mathsquiz.quizdetails.QuizDetailsActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setClickListeners()
    }

    private fun setClickListeners() {
        binding.learnMultipleTables.setOnClickListener {
            startQuizDetailsActivity(QuizType.MULTIPLE_TABLE)
        }

        binding.learnOneTable.setOnClickListener {
            startQuizDetailsActivity(QuizType.ONE_TABLE)
        }

        binding.learnSquares.setOnClickListener {
            startQuizDetailsActivity(QuizType.SQUARES)
        }

        binding.learnCubes.setOnClickListener {
            startQuizDetailsActivity(QuizType.CUBES)
        }
    }

    private fun startQuizDetailsActivity(quizType: QuizType) {
        val intent = Intent(this, QuizDetailsActivity::class.java)
        intent.putExtra("QUIZ_TYPE", quizType)
        startActivity(intent)
    }

}