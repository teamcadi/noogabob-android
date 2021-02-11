package com.example.noogabab.presentation.ui.start.enterGroup

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.noogabab.R
import com.example.noogabab.util.DynamicTextWatcher
import kotlinx.android.synthetic.main.activity_create_group.*
import kotlinx.android.synthetic.main.fragment_create_user.*

class CreateUserFragment : Fragment(R.layout.fragment_create_user), View.OnClickListener {
    private val viewModel: EnterGroupViewModel by activityViewModels()
    private val textWatcher = DynamicTextWatcher(
        onChanged = { _, _, _, _ ->
            viewModel.updateName(edit_start_name.text.toString())
            viewModel.updateRole(edit_start_role.text.toString())
        }
    )
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        load()
    }

    private fun load() {
        edit_start_name.addTextChangedListener(textWatcher)
        edit_start_role.addTextChangedListener(textWatcher)
        btn_create_user_to_dog.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        (activity as EnterGroupActivity).nextPage()
    }
}