package com.compose.mathsquiz

import android.view.View
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter

@BindingAdapter("bindVisibleorGone")
fun bindVisibleorGone(view: View, visibility: Boolean) {
    view.isVisible = visibility
}