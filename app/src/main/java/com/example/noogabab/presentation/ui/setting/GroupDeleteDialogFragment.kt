package com.example.noogabab.presentation.ui.setting

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.provider.Settings.Global.getString
import androidx.fragment.app.DialogFragment
import com.example.noogabab.R

class GroupDeleteDialogFragment : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        AlertDialog.Builder(requireContext())
            .setMessage("그룹을 삭제하시겠습니까?")
            .setPositiveButton("확인") { _, _ -> }
            .setNegativeButton("취소") { _, _ -> }
            .create()

    companion object {
        const val TAG = "GroupDeleteDialog"
    }
}