package com.compose.mathsquiz.domain

data class Table(
    val firstNo: Int?,
    val secondNo: Int?,
    val quizType: QuizType?
) {
    val answer = when (quizType) {
        QuizType.ONE_TABLE, QuizType.MULTIPLE_TABLE -> secondNo?.let { firstNo?.times(it) }
        QuizType.SQUARES -> firstNo?.times(firstNo)
        QuizType.CUBES -> firstNo?.times(firstNo)?.times(firstNo)
        else -> null
    }
}
