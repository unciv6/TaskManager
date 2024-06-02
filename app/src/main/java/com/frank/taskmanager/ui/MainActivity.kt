package com.frank.taskmanager.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.frank.taskmanager.R
import com.frank.taskmanager.databinding.ActivityMainBinding
import com.frank.taskmanager.four.FourDayFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportFragmentManager.beginTransaction().apply {
//            add(R.id.container, MainFragment())
            add(R.id.container, FourDayFragment())

            commit()
        }
    }
}