package com.compose.mathsquiz

import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity

fun AppCompatActivity.hideKeyboard() {
    val view = this.currentFocus
    val methodManager = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    assert(view != null)
    methodManager.hideSoftInputFromWindow(view!!.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
}

fun AppCompatActivity.showKeyboard() {
    val view = this.currentFocus
    val methodManager = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    assert(view != null)
    methodManager.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
}