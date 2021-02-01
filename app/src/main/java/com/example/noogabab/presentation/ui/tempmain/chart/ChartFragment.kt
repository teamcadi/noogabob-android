package com.example.noogabab.presentation.ui.tempmain.chart

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.noogabab.R
import com.example.noogabab.presentation.ui.start.enterGroup.ViewPagerAdapter
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_chart.*
import kotlinx.android.synthetic.main.fragment_week_chart.*

class ChartFragment() : Fragment(R.layout.fragment_chart) {
    private val tabTextList = listOf("주간", "월간")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().window.statusBarColor = requireActivity().getColor(R.color.color_feeedc);
        loadTab()
    }

    private fun loadTab() {
        val adapter = ViewPagerAdapter(requireActivity())
        adapter.addFragment(WeekChartFragment())
        adapter.addFragment(MonthChartFragment())
        vp_chart.adapter = adapter
        TabLayoutMediator(tab_chart, vp_chart) { t, p -> t.text = tabTextList[p] }.attach()
    }


}