package com.example.noogabab.presentation.ui.start.create

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.ArrayAdapter
import com.example.noogabab.R
import com.example.noogabab.presentation.entity.PresenterBabTime
import kotlinx.android.synthetic.main.fragment_craete_dog.*


class CreateDogFragment : Fragment(R.layout.fragment_craete_dog) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dogs = resources.getStringArray(R.array.dogs)

        val arrayAdapter = ArrayAdapter<String>(requireContext(), android.R.layout.simple_list_item_1, dogs)
        autoCompleteTextView.setAdapter(arrayAdapter)

        val items = mutableListOf<PresenterBabTime>()
        items.add(PresenterBabTime("첫 끼", "오전", "09:00"))
        items.add(PresenterBabTime("두 끼", "오후", "01:00"))
        items.add(PresenterBabTime("세 끼", "오후", "07:00"))
        list_view_bab_time.adapter = CreateDogListAdapter(items)
    }
}