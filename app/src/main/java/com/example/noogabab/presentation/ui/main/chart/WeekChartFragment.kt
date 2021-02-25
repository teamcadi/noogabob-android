package com.example.noogabab.presentation.ui.main.chart

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.view.get
import androidx.fragment.app.activityViewModels
import com.example.noogabab.R
import com.example.noogabab.data.api.model.ResultData
import com.example.noogabab.util.SharedGroup
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import com.google.android.material.datepicker.MaterialDatePicker
import kotlinx.android.synthetic.main.fragment_week_chart.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class WeekChartFragment : Fragment(R.layout.fragment_week_chart), View.OnClickListener {
    private val chartViewModel: ChartViewModel by activityViewModels<ChartViewModel>()
    private lateinit var sharedGroup: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedGroup = requireActivity().getSharedPreferences(SharedGroup.NAME, Context.MODE_PRIVATE);
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        load()
        observe()
    }

    private fun load() {
        btn_week_rank_snack.setOnClickListener(this)
        btn_week_rank_bob.setOnClickListener(this)
        btn_select_week_date.setOnClickListener(this)
    }

    private fun observe() {
        chartViewModel.currentDate.observe(requireActivity(), androidx.lifecycle.Observer {
            getStatistics(it)
        })
        chartViewModel.currentXGroups.observe(requireActivity(), androidx.lifecycle.Observer {
            setBarChartValues(it, chartViewModel.currentYBobs!!, chartViewModel.currentYSnacks!!)
            for (i in 0 until it.size) linear_week_rank.addView(createRankImage())
        })
    }

    private fun getStatistics(date: String) {
        val key = sharedGroup.getString(SharedGroup.GROUP_UUID_KEY, "")
        val groupId = sharedGroup.getInt(SharedGroup.GROUP_ID_KEY, -1)

        chartViewModel.getStatistics(key!!, groupId, "week", date).observe(requireActivity(), androidx.lifecycle.Observer { resultData ->
            when(resultData) {
                is ResultData.Loading -> {}
                is ResultData.Success -> {
                    val mealsData = resultData.data!!.statisticsData!!.mealRankData!!
                    val snacksData = resultData.data!!.statisticsData!!.snackRankData!!
                    val users = mealsData.map { it.name!! }
                    val mealsCount = mealsData.map { it.cnt!!.toFloat() }
                    val snacksCount = snacksData.map { it.cnt!!.toFloat() }
                    chartViewModel.updateChart(
                        users as ArrayList<String>,
                        mealsCount.toFloatArray(),
                        snacksCount.toFloatArray()
                    )
                }
                else -> {
                    Toast.makeText(requireContext(), "err", Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun getFirst(size: Int, position: Int) {
        for (i in 0 until size) linear_week_rank[i].visibility = View.INVISIBLE
        linear_week_rank[position].visibility = View.VISIBLE
    }

    private fun createRankImage(): ImageView {
        val rankImage = ImageView(context)
        rankImage.visibility = View.INVISIBLE
        rankImage.setImageResource(R.drawable.ic_first_xxxhd)
        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        params.weight = 1f
        rankImage.layoutParams = params
        return rankImage
    }

    private fun setBarChartValues(
        xValues: ArrayList<String>,
        yAxis1: FloatArray,
        yAxis2: FloatArray
    ) {
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

    override fun onClick(view: View?) {
        when(view) {
            btn_select_week_date -> selectDate()
            btn_week_rank_bob -> {}
            btn_week_rank_snack -> {}
        }
    }

//    private fun bobRankClick() {
//        var position = 0
//        var max = -1f
//        val yBobs = chartViewModel.currentYBobs!!
//        for (i in yBobs.indices) if (max < yBobs[i]) {
//            max = yBobs[i]; position = i
//        }
//        getFirst(yBobs.size, position)
//    }

//    private fun snackRankClick() {
//        var position = 0
//        var max = -1f
//        val ySnacks = chartViewModel.currentYSnacks!!
//        for (i in ySnacks.indices) if (max < ySnacks[i]) {
//            max = ySnacks[i]; position = i
//        }
//        getFirst(ySnacks.size, position)
//    }

    private fun selectDate() {
        val datePicker = MaterialDatePicker.Builder.datePicker()
            .setTitleText("날짜를 선택하세요")
            .build()
        datePicker.show(requireActivity().supportFragmentManager, "DATE_PICKER")
        datePicker.addOnPositiveButtonClickListener {
            val sdf = SimpleDateFormat("yyyy-MM-dd")
            val formatTime = sdf.format(Date(it))
            chartViewModel.updateDate(formatTime)
        }
    }
}