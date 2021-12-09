package com.example.myphoneoptimizer

import com.google.gson.Gson
import java.io.File
import java.io.InputStream
import java.math.BigDecimal
import java.math.RoundingMode
import java.text.DecimalFormat
import kotlin.math.round

/**
 * Clase que se encarga de guardar y manejar la informacion relativa a la memoria RAM del dispositivo
 */
class RAM_INFO {
  var MemTotal: Int =0
  var MemFree: Int =0
  var MemAvailable: Int =0
  var Buffers: Int =0
  var Cached: Int =0
  var SwapCached: Int =0
  override fun toString(): String {
    var tmp = ""
    tmp += "MemTotal:"+MemTotal+"\n"
    tmp += "MemFree:"+MemFree+"\n"
    tmp += "MemAvailable:"+MemAvailable+"\n"
    tmp += "Buffers:"+Buffers+"\n"
    tmp += "Cached:"+Cached+"\n"
    tmp += "SwapCached:"+SwapCached+"\n"
    return tmp
  }
  fun toStringGB():String{
    var tmp = ""
    tmp += "MemTotal:"+KBtoGB(MemTotal)+"\n"
    tmp += "MemFree:"+KBtoGB(MemFree)+"\n"
    tmp += "MemAvailable:"+KBtoGB(MemAvailable)+"\n"
    tmp += "Buffers:"+KBtoGB(Buffers)+"\n"
    tmp += "Cached:"+KBtoGB(Cached)+"\n"
    tmp += "SwapCached:"+KBtoGB(SwapCached)+"\n"
    return tmp
  }

  /**
   * Este metodo sirve para convertir una cantidad de kB a GB
   * @param valor es el valor que se quiere obtener su equivalente en GB
   * @return Retorna el valor en GB
   */
  fun KBtoGB(valor:Int):Double{
    var tmp:Double =(valor.toDouble()/1048576.toDouble())
    val df = DecimalFormat("#.##")
    df.roundingMode = RoundingMode.CEILING
    var s = df.format(tmp).replace(",",".")
    return s.toDouble()
  }

  /**
   * Este metodo sirve para leer la linea de un fichero que este delimitado por ":" y devolver esa misma linea en formato JSON
   * @param linea string de la linea leida del fichero
   * @param delimitador string del delimitador a encontrar
   * @return el string listo para ser parseado a JSON
   */
  companion object {
    fun memParcer():RAM_INFO{
      val inputStream: InputStream = File("/proc/meminfo").inputStream()
      val lineList = mutableListOf<String>()
      var ramInfo = RAM_INFO()
      inputStream.bufferedReader().useLines {
          lines -> lines.forEach {
            lineList.add(it)
          }
      }
      inputStream.close()
      var info =""
      lineList.forEach{
        var valor = RAM_INFO.dividirLinea(it,":")
        if(valor != ""){
          info +=RAM_INFO.dividirLinea(it,":")
          info +=","
        }
      }
      info = info.dropLast(1)
      info = "{"+info+"}"
      ramInfo = Gson().fromJson(info,RAM_INFO::class.java)
      return ramInfo
    }

    fun dividirLinea(linea:String, delimitador:String):String{
      var objeto =""
      val lista:Array<String> = arrayOf("MemTotal","MemFree","MemAvailable","Buffers","Cached","SwapCached")
      if(linea.length !=0){
        var linea = linea.dropLast(3)
        var pos = linea.indexOf(delimitador)
        var indice = linea.substring(0,pos)
        if(indice in lista){
          val valor = linea.substring(pos+1)
          objeto ='"'+indice+'"'+':'+'"'+valor+'"'
        }
      }
      return objeto
    }
  }



}