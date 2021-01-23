package com.example.noogabab.presentation.ui.start.createUser

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.noogabab.R
import com.example.noogabab.presentation.ui.start.enterGroup.EnterGroupActivity
import kotlinx.android.synthetic.main.fragment_create_user.*

class CreateUserFragment : Fragment(R.layout.fragment_create_user) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        nextPage()
    }

    private fun nextPage() {
        btn_create_user_to_dog.setOnClickListener {
            (activity as EnterGroupActivity).nextPage()
        }
    }
}