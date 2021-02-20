package com.example.noogabab.presentation.ui.main.chart

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.noogabab.R
import com.example.noogabab.presentation.ui.start.enterGroup.EnterGroupViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_chart.*

class ChartFragment : Fragment(R.layout.fragment_chart) {
    private val tabTextList = listOf("주간", "월간")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().window.statusBarColor = requireActivity().getColor(R.color.color_feeedc);
        loadTab()
    }

    private fun loadTab() {
        val adapter = EnterGroupViewPagerAdapter(requireActivity())
        adapter.addFragment(WeekChartFragment())
        adapter.addFragment(MonthChartFragment())
        vp_chart.adapter = adapter
        TabLayoutMediator(tab_chart, vp_chart) { t, p -> t.text = tabTextList[p] }.attach()
    }
}