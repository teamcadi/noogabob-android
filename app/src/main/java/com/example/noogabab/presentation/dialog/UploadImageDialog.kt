package com.example.noogabab.presentation.dialog

import android.app.Dialog
import android.content.Context
import android.view.View
import android.view.Window
import androidx.core.content.res.ResourcesCompat
import com.example.noogabab.R
import kotlinx.android.synthetic.main.dialog_upload_image.*

class UploadImageDialog constructor(
    context: Context,
    private val getCamera: () -> Unit,
    private val getGallery: () -> Unit
) : Dialog(context), View.OnClickListener {
    init {
        setCanceledOnTouchOutside(false)
        setCancelable(false)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.dialog_upload_image)

        val size = context.resources.displayMetrics
        window!!.attributes.width = (size.widthPixels * 0.7).toInt()
        window!!.attributes.height = (size.heightPixels * 0.441).toInt()
        window!!.setBackgroundDrawable(
            ResourcesCompat.getDrawable(
                context.resources,
                R.drawable.dialog_background,
                null
            )
        )

        btn_get_camera.setOnClickListener(this)
        btn_get_gallery.setOnClickListener(this)
        btn_dialog_upload_close.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view) {
            btn_get_camera -> {
                getCamera()
                dismiss()
            }
            btn_get_gallery -> {
                getGallery()
                dismiss()
            }
            btn_dialog_upload_close -> {
                dismiss()
            }
        }
    }
}