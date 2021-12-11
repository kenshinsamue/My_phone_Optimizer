package com.example.myphoneoptimizer

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class Sistema : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_sistema)

    findViewById<TextView>(R.id.textView14).apply {
      text = Build.PRODUCT
    }
    findViewById<TextView>(R.id.textView13).apply {
      text = android.os.Build.BRAND
    }
    findViewById<TextView>(R.id.dispositivo).apply {
      text = android.os.Build.DEVICE
    }
    findViewById<TextView>(R.id.fabricante).apply{
      text= Build.MANUFACTURER
    }
    findViewById<TextView>(R.id.modelo).apply {
      text = android.os.Build.MODEL
    }
    var myCPU = CPU_INFO.cpuParser()
    findViewById<TextView>(R.id.informacion_).apply {
      text = myCPU.toString()
    }
    var myRAM = RAM_INFO.memParcer()
    findViewById<TextView>(R.id.RAM_info).apply {
      text = myRAM.toStringGB()
    }
  }
}