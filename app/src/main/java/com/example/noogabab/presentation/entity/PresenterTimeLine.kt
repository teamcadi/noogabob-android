package com.example.noogabab.presentation.entity

import android.graphics.drawable.Drawable

data class PresenterTimeLine(
    val time: Long,
    val icon: Drawable,
    val content: String,
    val subContent: String)