package com.frank.taskmanager.four

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.frank.taskmanager.R
import java.time.DayOfWeek
import java.time.LocalDate

/**
 * author: frankzhang
 * created on: 2024/6/2 22:53
 * description:
 */
class CeilAdapter : RecyclerView.Adapter<MyHolder>() {

    lateinit var datas: List<Ceil>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        return MyHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_day, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.update(datas[position])
    }
}

class MyHolder(itemView: View) : ViewHolder(itemView) {

    var month = itemView.findViewById<TextView>(R.id.month)
    var day1: TextView = itemView.findViewById<TextView>(R.id.day1)
    var day2: TextView = itemView.findViewById<TextView>(R.id.day2)
    var day3: TextView = itemView.findViewById<TextView>(R.id.day3)
    var day4: TextView = itemView.findViewById<TextView>(R.id.day4)

    var groupA: TextView = itemView.findViewById<TextView>(R.id.group_one)
    var groupB: TextView = itemView.findViewById<TextView>(R.id.group_sec)

    var isRedBackground = false
    var isRedBackground2 = false


    @RequiresApi(Build.VERSION_CODES.O)
    fun update(ceil: Ceil) {
        day1.text = "" + ceil.dates[0].dayOfMonth
        day2.text = "" + ceil.dates[1].dayOfMonth
        day3.text = "" + ceil.dates[2].dayOfMonth
        day4.text = "" + ceil.dates[3].dayOfMonth
        var str = "" + ceil.dates[3].monthValue + "月"
        if (ceil.dates[3].year > ceil.dates[0].year) {
            str = "" + ceil.dates[3].year
            month.setTextColor(Color.RED)
        } else if (ceil.dates[0].monthValue == 1 && ceil.dates[0].dayOfMonth == 1) {
            str = "" + ceil.dates[0].year
            month.setTextColor(Color.RED)
        } else if (ceil.dates[3].monthValue > ceil.dates[0].monthValue) {
            month.setTextColor(Color.BLUE)
        } else if (ceil.dates[0].dayOfMonth == 1) {
            month.setTextColor(Color.BLUE)
        } else {
            month.setTextColor(Color.GRAY)
        }
        month.text = str

        val days = listOf(day1, day2, day3, day4)

        ceil.dates.forEachIndexed { index, date ->
            val textView = days[index]
            if (date.isWeekend()) {
                textView.setTextColor(Color.RED)
            } else {
                textView.setTextColor(Color.GRAY)
            }
            if (date.isToday()) {
                textView.setTextColor(Color.WHITE)
                textView.setBackgroundResource(R.drawable.rounded_background)
            } else {
                textView.setBackgroundResource(android.R.color.transparent) // 如果不是今天，移除背景或设置成透明
            }
        }

        groupA.setOnClickListener {
            if (isRedBackground) {
                groupA.setBackgroundResource(android.R.color.transparent)
            } else {
                groupA.setBackgroundResource(R.drawable.rounded_background_holo)
            }
            isRedBackground = !isRedBackground
        }

        groupB.setOnClickListener {
            if (isRedBackground2) {
                groupB.setBackgroundResource(android.R.color.transparent)
            } else {
                groupB.setBackgroundResource(R.drawable.rounded_background_holo2)
            }
            isRedBackground2 = !isRedBackground2
        }
    }

    private fun createRoundedBackground(color: Int): GradientDrawable {
        return GradientDrawable().apply {
            shape = GradientDrawable.RECTANGLE
            setColor(color)
            cornerRadius = 10f // 设置圆角半径，可以根据需要调整
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun LocalDate.isToday(): Boolean {
        return this == LocalDate.now()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun LocalDate.isWeekend(): Boolean {
        return this.dayOfWeek == DayOfWeek.SATURDAY || this.dayOfWeek == DayOfWeek.SUNDAY
    }
}
