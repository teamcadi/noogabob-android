package com.example.noogabab.presentation.dialog

import android.app.Activity
import android.app.Dialog
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.view.Window
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import com.example.noogabab.R
import kotlinx.android.synthetic.main.fragment_dialog.*

class CreateGroupDialog constructor(context: Context): Dialog(context) {
    init {
        setCanceledOnTouchOutside(false)
        setCancelable(false)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.fragment_dialog)

        val size = context.resources.displayMetrics
        window!!.attributes.width = (size.widthPixels * 0.7).toInt()
        window!!.attributes.height = (size.heightPixels * 0.441).toInt()
        window!!.setBackgroundDrawable(ResourcesCompat.getDrawable(context.resources, R.drawable.dialog_background, null))
        closeClick()
        cloneClick()
    }

    private fun closeClick() {
        btn_dialog_close.setOnClickListener {
            dismiss()
        }
    }

    private fun cloneClick() {
        btn_dialog_clone.setOnClickListener {
            val text = txt_dialog_key.text
            val clipboardManager: ClipboardManager = context.getSystemService(Activity.CLIPBOARD_SERVICE) as ClipboardManager
            val clipData: ClipData = ClipData.newPlainText("create_key", text)
            clipboardManager.setPrimaryClip(clipData)
            Toast.makeText(context, "$text 복사 완료", Toast.LENGTH_SHORT).show()
        }
    }
}