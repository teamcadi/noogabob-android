package com.example.noogabab.presentation.ui.start.createDog

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.ArrayAdapter
import com.example.noogabab.R
import kotlinx.android.synthetic.main.fragment_craete_dog.*


class CreateDogFragment : Fragment(R.layout.fragment_craete_dog) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dogs = resources.getStringArray(R.array.dogs)

        val arrayAdapter = ArrayAdapter<String>(requireContext(), android.R.layout.simple_list_item_1, dogs)
        readonly_dog_kind.setAdapter(arrayAdapter)
    }
}