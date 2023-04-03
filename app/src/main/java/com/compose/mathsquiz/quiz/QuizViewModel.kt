package com.compose.mathsquiz.quiz

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.compose.mathsquiz.domain.QuizType
import com.compose.mathsquiz.domain.Table
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class QuizViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val startTableNo = savedStateHandle["START_NO"] ?: 2
    private val endTableNo = savedStateHandle["END_NO"] ?: 50
    private val quizType = savedStateHandle["QUIZ_TYPE"] ?: QuizType.NONE

    private val _table = MutableLiveData<Table>()
    val table: LiveData<Table>
        get() = _table

    fun setupQuestion() {
        val result = when (quizType) {
            QuizType.MULTIPLE_TABLE -> setupMultipleTableQuestion()

            QuizType.ONE_TABLE -> setupOneTableQuestion()

            QuizType.SQUARES, QuizType.CUBES -> setupSquareOrCubeQuestion()

            else -> {
                Pair(null, null)
            }
        }
        _table.value = Table(result.first, result.second, quizType)
        Log.d(TAG, "setupQuestion: ${savedStateHandle.get<QuizType>("QUIZ_TYPE")}")

    }

    private fun setupMultipleTableQuestion(): Pair<Int?, Int?> {
        val firstNo = (startTableNo.rangeTo(endTableNo)).random()
        val secondNo = (2..10).random()
        return Pair(firstNo, secondNo)
    }

    private fun setupOneTableQuestion(): Pair<Int?, Int?> {
        val secondNo = (2..10).random()
        return Pair(startTableNo, secondNo)
    }

    private fun setupSquareOrCubeQuestion(): Pair<Int?, Int?> {
        val firstNo = (startTableNo.rangeTo(endTableNo)).random()
        return Pair(firstNo, null)
    }


    companion object {
        const val TAG = "QuizViewModel"
    }
}