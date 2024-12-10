package com.example.demoapp.utils

import android.graphics.Color
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.demoapp.R
import com.google.android.material.snackbar.Snackbar

fun View.showSnack(msg: String) {

    try {
        val snackBar = Snackbar.make(this, msg, Snackbar.LENGTH_SHORT)
        val snackBarView = snackBar.view
        val textView = snackBarView.findViewById<View>(com.google.android.material.R.id.snackbar_text) as TextView
        textView.maxLines = 3
        snackBar.setAction(R.string.okay) { snackBar.dismiss() }
        snackBarView.setBackgroundColor(Color.parseColor("#27242b"))
        snackBar.setActionTextColor(ContextCompat.getColor(context, R.color.color_green))
        snackBar.show()
    } catch (e: Exception) {
        e.printStackTrace()
    }
}