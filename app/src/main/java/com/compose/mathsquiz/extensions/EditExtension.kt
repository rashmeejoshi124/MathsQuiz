package com.compose.mathsquiz

import android.view.inputmethod.EditorInfo
import androidx.appcompat.widget.AppCompatEditText

fun AppCompatEditText.onSubmit(func: () -> Unit) {
    setOnEditorActionListener { _, actionId, _ ->

        if (actionId == EditorInfo.IME_ACTION_DONE) {
            func()
        }
        true
    }
}