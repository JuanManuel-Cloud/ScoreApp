package com.moni.scoreapp.utils

import android.view.View
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.moni.scoreapp.R

object Others {
    fun showErrorSnackbar(rootView: View, msg: String, duration: Int = Snackbar.LENGTH_LONG) {
        Snackbar.make(
            rootView,
            msg,
            duration
        ).apply {
            this.setBackgroundTint(ContextCompat.getColor(context, R.color.error50))
            this.setTextColor(ContextCompat.getColor(context, R.color.white))
            show()
        }
    }
}