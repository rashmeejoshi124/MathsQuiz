package com.compose.mathsquiz.quiz

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import com.compose.mathsquiz.databinding.ActivityQuizBinding
import com.compose.mathsquiz.domain.QuizType
import com.compose.mathsquiz.domain.Table
import com.compose.mathsquiz.onSubmit
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class QuizActivity : AppCompatActivity() {

    private val vm by viewModels<QuizViewModel>()
    private lateinit var binding: ActivityQuizBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)
        savedInstanceState?.putAll(intent.extras)
        vm.setupQuestion()
        binding.answer.requestFocus()
        setAnswerListener()
    }


    private fun setAnswerListener() {
        vm.table.observe(this) {
            binding.question.text = getQuestionText(it)
            setHint()
        }

        binding.submit.setOnClickListener {
            handleSubmit()
        }

        binding.hint.setOnClickListener {
            it.performLongClick()
            CoroutineScope(Dispatchers.Main).launch {
                delay(500L)
                binding.hint.tooltipText = null
                setHint()
            }
        }

        binding.answer.doOnTextChanged { text, start, before, count ->
            if (count > 0 || isAnswerCorrect) {
                binding.answerLayout.error = null
            }
        }

        binding.answer.onSubmit { handleSubmit() }
    }

    private fun getQuestionText(table: Table?) = when (table?.quizType) {
        QuizType.ONE_TABLE, QuizType.MULTIPLE_TABLE -> "${table.firstNo} X ${table.secondNo}"

        QuizType.SQUARES -> "${table.firstNo}\u00B2"

        QuizType.CUBES -> "${table.firstNo}\u00B3"

        else -> ""
    }

    private val isAnswerCorrect: Boolean
        get() =
            if (binding.answer.text?.isNotEmpty() == true)
                binding.answer.text.toString().toInt() == vm.table.value?.answer
            else
                false

    private fun handleSubmit() {
        if (isAnswerCorrect) {
            Toast.makeText(this, "Correct Answer", Toast.LENGTH_SHORT).show()
            showNextQuestion()
        } else {
            Toast.makeText(this, "Wrong Answer", Toast.LENGTH_SHORT).show()
            binding.answer.text = null
            binding.answerLayout.error = "Wrong Answer. Please try again."
        }
    }

    private fun showNextQuestion() {
        binding.answer.text = null
        vm.setupQuestion()
    }

    private fun setHint() {
        binding.hint.tooltipText = "Answer: ${vm.table.value?.answer.toString()}"
    }


}