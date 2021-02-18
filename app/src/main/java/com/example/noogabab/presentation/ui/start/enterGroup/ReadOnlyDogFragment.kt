package com.example.noogabab.presentation.ui.start.enterGroup

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.noogabab.R
import com.example.noogabab.data.api.model.ResultData
import com.example.noogabab.presentation.ui.main.MainActivity
import com.example.noogabab.presentation.ui.start.StartViewModel
import kotlinx.android.synthetic.main.fragment_craete_dog.*

class ReadOnlyDogFragment :
    Fragment(R.layout.fragment_craete_dog), View.OnClickListener {
    private val enterGroupViewModel: EnterGroupViewModel by activityViewModels<EnterGroupViewModel>()
    private val startViewModel: StartViewModel by activityViewModels<StartViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_enter_group.setOnClickListener(this)
        observe()
    }

    private fun observe() {
        enterGroupViewModel.currentBtnState.observe(requireActivity(), Observer {
            btn_enter_group.isEnabled = it
            if (it) btn_enter_group.setBackgroundColor(requireActivity().getColor(R.color.color_aa5900))
            else btn_enter_group.setBackgroundColor(requireActivity().getColor(R.color.color_e7d0b7))
        })
        startViewModel.getCurrentKey()?.let { enterGroupViewModel.getDog(it).observe(requireActivity(), Observer { resultData ->
            when(resultData) {
                is ResultData.Loading -> { }
                is ResultData.Success -> {}
                is ResultData.Failed -> {}
                is ResultData.Exception -> {}
            }
        }) }
        enterGroupViewModel.currentDog.observe(requireActivity(), Observer {

        })
    }

    override fun onClick(p0: View?) {
        val i = Intent(activity, MainActivity::class.java)
        startActivity(i)
    }
}