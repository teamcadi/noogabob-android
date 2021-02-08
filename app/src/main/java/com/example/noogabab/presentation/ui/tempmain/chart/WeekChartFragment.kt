package com.example.noogabab.presentation.ui.tempmain.chart

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.noogabab.R
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import kotlinx.android.synthetic.main.fragment_month_chart.*
import kotlinx.android.synthetic.main.fragment_week_chart.*

class WeekChartFragment : Fragment(R.layout.fragment_week_chart) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setBarChartValues()
    }
    private fun setBarChartValues() {
        // x axis values
        val xValues = ArrayList<String>()
        xValues.add("나")
        xValues.add("엄마")
        xValues.add("아빠")
        xValues.add("누나")

        // y axis values or bar data
        val yAxis1 = arrayOf<Float>(2.0f, 6f, 7.8f, 3.4f)
        val yAxis2 = arrayOf<Float>(1.0f, 7f, 3.8f, 8.4f)


        // bar entries
        val barEntries1 = ArrayList<BarEntry>()
        val barEntries2 = ArrayList<BarEntry>()
        for (i in yAxis1.indices) barEntries1.add(BarEntry(yAxis1[i], i))
        for (i in yAxis2.indices) barEntries2.add(BarEntry(yAxis2[i], i))

        // barData set
        val barDataSet1 = BarDataSet(barEntries1, "밥")
        val barDataSet2 = BarDataSet(barEntries2, "간식")
        barDataSet1.color = resources.getColor(R.color.color_aa5900)
        barDataSet2.color = resources.getColor(R.color.color_ffb254)

        val finalBarDataSet = ArrayList<BarDataSet>()
        finalBarDataSet.add(barDataSet1)
        finalBarDataSet.add(barDataSet2)

        // make a bar data
        val barData = BarData(xValues, finalBarDataSet as List<IBarDataSet>)
//        barData.groupSpace = 30f
        chart_week_bar.apply {
            data = barData
            setBackgroundColor(Color.WHITE)
            animateXY(3000, 3000)
            setDrawGridBackground(false)
            setTouchEnabled(false)
            setDescription("")
            extraBottomOffset = 20f

            xAxis.apply {
                textSize = 14f
                position = XAxis.XAxisPosition.BOTTOM
            }
            axisLeft.apply {
                textSize = 10f
                setDrawGridLines(true)
            }
            axisRight.apply {
                setDrawGridLines(false)
//                setDrawAxisLine(false)
                isEnabled = false
                setDrawLabels(false)
            }
            legend.apply {
                isEnabled = false
                formSize = 15f
                form = Legend.LegendForm.CIRCLE
                xEntrySpace = 30f
                textSize = 14f
                position = Legend.LegendPosition.ABOVE_CHART_CENTER
            }
        }
    }
}