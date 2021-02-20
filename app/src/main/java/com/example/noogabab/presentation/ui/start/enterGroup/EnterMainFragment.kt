package com.example.noogabab.presentation.ui.start.enterGroup

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.noogabab.R
import com.example.noogabab.presentation.ui.main.MainActivity
import com.example.noogabab.util.SharedDog
import com.example.noogabab.util.SharedGroup
import com.example.noogabab.util.SharedUser
import kotlinx.android.synthetic.main.fragment_enter_main.*

class EnterMainFragment :
    Fragment(R.layout.fragment_enter_main), View.OnClickListener {
    private val enterGroupViewModel: EnterGroupViewModel by activityViewModels<EnterGroupViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        load()
        observe()
    }

    private fun load() {
        btn_enter_group.setOnClickListener(this)
        val sharedDog = requireActivity().getSharedPreferences(SharedDog.NAME, Context.MODE_PRIVATE)
        readonly_dog_name.setText(sharedDog.getString(SharedDog.DOG_NAME_KEY, ""))
        readonly_dog_age.setText(sharedDog.getInt(SharedDog.DOG_AGE_KEY, 0).toString())
        readonly_dog_kind.setText(sharedDog.getString(SharedDog.DOG_KIND_KEY, ""))
        val meals = sharedDog.getString(SharedDog.DOG_MEALS_KEY, "")
        for (i in meals!!.split(",")) {}
    }

    private fun observe() {
        enterGroupViewModel.currentBtnState.observe(requireActivity(), Observer {
            btn_enter_group.isEnabled = it
            if (it) btn_enter_group.setBackgroundColor(requireActivity().getColor(R.color.color_aa5900))
            else btn_enter_group.setBackgroundColor(requireActivity().getColor(R.color.color_e7d0b7))
        })
    }

    override fun onClick(p0: View?) {
        val i = Intent(activity, MainActivity::class.java)
        startActivity(i)
    }
}