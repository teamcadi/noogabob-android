package com.example.noogabab.presentation.dialog

import android.app.Activity
import android.content.Context
import androidx.core.content.ContextCompat
import com.example.awesomedialog.*
import com.example.noogabab.R

class AlertDialog {
    fun defaultAlert(
        context: Context,
        title: String,
        content: String,
        ok: String,
        cancel: String,
        onPos: () -> Unit,
        onNeg: () -> Unit
    ) {
        AwesomeDialog.build(context as Activity)
            .title(title, titleColor = ContextCompat.getColor(context, android.R.color.black))
            .body(content)
            .icon(R.drawable.ic_logo)
            .onPositive(ok, buttonBackgroundColor = R.drawable.layout_rounded_pos) { onPos() }
            .onNegative(
                cancel,
//                textColor = ContextCompat.getColor(context, android.R.color.black),
                buttonBackgroundColor = R.drawable.layout_rounded_neg
            ) { onNeg() }
    }
}