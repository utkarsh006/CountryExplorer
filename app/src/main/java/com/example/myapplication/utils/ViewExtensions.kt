package com.example.myapplication.utils

import android.content.Context
import android.widget.Toast

object ViewExtensions {

    fun showToast(
        context: Context,
        message: String,
        isLongerDuration: Boolean = false
    ) {
        Toast.makeText(
            context,
            message,
            if (isLongerDuration) Toast.LENGTH_LONG else Toast.LENGTH_SHORT
        ).show()
    }

}