package com.compose.mathsquiz.quizdetails

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.compose.mathsquiz.databinding.ActivityQuizDetailsBinding
import com.compose.mathsquiz.domain.QuizType
import com.compose.mathsquiz.quiz.QuizActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QuizDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityQuizDetailsBinding
    private var quizType: QuizType? = null
    private val vm by viewModels<QuizDetailsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizDetailsBinding.inflate(layoutInflater)
        binding.vm = vm
        setContentView(binding.root)
        setClickListeners()
        quizType = intent?.getSerializableExtra("QUIZ_TYPE", QuizType::class.java)
        quizType?.let { vm.setUIDetails(it) }
    }

    private fun setClickListeners() {
        binding.letsGoBtn.setOnClickListener {
            startQuizActivity()
        }

        vm.shouldShowEditBox.observe(this) {
            when {
                it.first ->
                    binding.startTableNo.requestFocus()
                it.second ->
                    binding.endTableNo.requestFocus()
            }
        }
    }

    private fun startQuizActivity() {
        val intent = Intent(this, QuizActivity::class.java)
        intent.putExtra("QUIZ_TYPE", quizType)
        val startNo: Int? = if (binding.startTableNo.text.toString().isNotEmpty())
            binding.startTableNo.text.toString().toInt() else null
        val endNo: Int? = if (binding.endTableNo.text.toString().isNotEmpty())
            binding.endTableNo.text.toString().toInt() else null
        startNo?.let { intent.putExtra("START_NO", startNo) }
        endNo?.let { intent.putExtra("END_NO", endNo) }
        startActivity(intent)
    }

}