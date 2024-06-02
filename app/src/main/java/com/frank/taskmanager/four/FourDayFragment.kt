package com.frank.taskmanager.four

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Orientation
import com.frank.taskmanager.databinding.FragmentFourBinding
import java.time.LocalDate
import java.time.Month
import java.util.Date


/**
 * author: frankzhang
 * created on: 2024/6/2 00:02
 * description:
 */
class FourDayFragment : Fragment() {

    private val TAG = "FourDayFragment"


    private lateinit var binding: FragmentFourBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFourBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        binding.title.text = hello()

        val layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        binding.recyclerView.layoutManager = layoutManager


        var adapter = CeilAdapter()
        binding.recyclerView.adapter = adapter
        adapter.datas = generateCalendarUntilYearEnd(2026)
        adapter.notifyDataSetChanged()
    }

    fun hello(): String {
        var builder = StringBuilder()

        val juneDates = generateCalendarUntilYearEnd(2026)
        for (line in juneDates) {
            builder.append(line).append("\n")
        }

        return builder.toString()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun generateCalendarUntilYearEnd(year: Int): List<Ceil> {
        val calendarLines = mutableListOf<Ceil>()
        val startDate = LocalDate.of(2024, Month.JUNE, 2)
        val cycleStartDate = startDate.minusDays(3)
        var date = cycleStartDate
        var dayCount = 0
        var lineDates = mutableListOf<LocalDate>()
        while (date.year <= year) {
            lineDates.add(date)
            dayCount++
            if (dayCount % 4 == 0) {
                val ceil = Ceil(lineDates)
                calendarLines.add(ceil)
                lineDates = mutableListOf<LocalDate>()
            }
            date = date.plusDays(1)
        }
        return calendarLines
    }
}