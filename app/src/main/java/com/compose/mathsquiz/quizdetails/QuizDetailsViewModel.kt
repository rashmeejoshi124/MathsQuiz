package com.compose.mathsquiz.quizdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.compose.mathsquiz.domain.QuizType
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class QuizDetailsViewModel @Inject constructor() : ViewModel() {

    private val _uiDetails = MutableLiveData<Triple<String, String, String?>>()
    val uiDetails: LiveData<Triple<String, String, String?>>
        get() = _uiDetails

    private val _shouldShowEditBox = MutableLiveData<Pair<Boolean, Boolean>>()
    val shouldShowEditBox: LiveData<Pair<Boolean, Boolean>>
        get() = _shouldShowEditBox

    fun setUIDetails(quizType: QuizType) {
        var title = ""
        var hint1 = ""
        var hint2 = ""
        when (quizType) {
            QuizType.MULTIPLE_TABLE -> {
                title = "Quiz yourself in Tables"
                hint1 = "Enter table start no"
                hint2 = "Enter table end no"
            }
            QuizType.ONE_TABLE -> {
                title = "Learn a Table by Practice"
                hint1 = "Enter table no"
            }
            QuizType.SQUARES -> {
                title = "Quiz yourself in Squares"
                hint2 = "Practice Squares till"
            }
            QuizType.CUBES -> {
                title = "Quiz yourself in Cubes"
                hint2 = "Practice Cubes till"
            }
            else -> {}
        }

        _uiDetails.value = Triple(title, hint1, hint2)
        _shouldShowEditBox.value = Pair(hint1.isNotEmpty(), hint2.isNotEmpty())
    }

}