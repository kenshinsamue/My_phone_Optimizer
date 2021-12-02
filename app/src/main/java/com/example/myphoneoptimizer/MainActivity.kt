package com.example.myphoneoptimizer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
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
    cpuParser()
  }


  /**
   * Este metodo sirve para buscar la informacion del CPU del dispositivo buscando el en fichero "/proc/cpuinfo"
   */
  private fun cpuParser(){
    val inputStream: InputStream = File("/proc/cpuinfo").inputStream()
    val lineList = mutableListOf<String>()
    val infoCPU = ""
    inputStream.bufferedReader().useLines {
      lines -> lines.forEach {
        lineList.add(it)
      }
    }
    println("---------------------------------->>>>>>>>>>>>")
    lineList.forEach{
      dividirLinea(it,":")
    }
  }

  /**
   * Este metodo sirve para leer la linea de un fichero que este delimitado por ":" y devolver esa misma linea en formato JSON
   * @param linea string de la linea leida del fichero
   * @param delimitador string del delimitador a encontrar
   * @return el string listo para ser parseado a JSON
   */
  private fun dividirLinea(linea:String, delimitador:String):String{
    var objeto =""
    if(linea.length !=0){
      var pos = linea.indexOf(delimitador)
      val valor = linea.substring(pos+1)
      var indice = linea.substring(0,pos-1)
      objeto ="{"+'"'+indice+'"'+':'+valor+'}'
    }
    return objeto
  }

}