package com.example.myphoneoptimizer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import java.io.BufferedReader
import java.io.File
import java.io.InputStream


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<TextView>(R.id.dispositivo).apply {
            text = android.os.Build.DEVICE
        }
        findViewById<TextView>(R.id.modelo).apply {
            text = android.os.Build.MODEL
        }


        val bufferedReader: BufferedReader = File("/proc/cpuinfo").bufferedReader()
        val inputString = bufferedReader.use {
            it.readText()
        }
        findViewById<TextView>(R.id.informacion_).apply {
            text = inputString
        }
        println(inputString)
//            val inputStream:  InputStream = File("/proc/cpuinfo").inputStream()
//            val inputStream = inputStream.bufferedReader().use{
//
//            }

//        info_modelo.textContent = android.os.Build.DEVICE


    }
}