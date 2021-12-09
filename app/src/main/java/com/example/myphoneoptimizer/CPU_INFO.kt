package com.example.myphoneoptimizer

import com.google.gson.Gson
import java.io.File
import java.io.InputStream

/**
 *  La clase CPU_INFO guarda toda la informacion del procesador del terminal donde se ejecuta la APP
 */
class CPU_INFO(){
  var cores:MutableList<CORE_INFO> = arrayListOf()
  var Processor: String=""
  var coresNumber:Int=0
  var Hardware:String=""
  override fun toString(): String {
    var tmp = ""
    tmp = "Processor : " + Processor +"\n"
    tmp += "Cores Number : "+ coresNumber + "\n"
    tmp += "Hardware : "+Hardware+"\n"
    tmp += "Cores: "+cores
    return tmp
  }
  companion object{
    /**
     * Este metodo sirve para buscar la informacion del CPU del dispositivo buscando el en fichero "/proc/cpuinfo"
     */
    fun cpuParser():CPU_INFO{
      val inputStream: InputStream = File("/proc/cpuinfo").inputStream()
      val lineList = mutableListOf<String>()
      var infoCPU = CPU_INFO()
      inputStream.bufferedReader().useLines {
          lines -> lines.forEach {
        lineList.add(it)
      }
      }
      println("---------------------------------->>>>>>>>>>>>")
      var linea = ""
      linea = dividirLinea(lineList[0],":")     // Linea = "indice" : "valor"
      var infocore:String =""
      var agregar:Boolean = false
      lineList.forEachIndexed{ index: Int, s: String ->
        if(index==0){
          linea = "{"+linea+"}"
          infoCPU = Gson().fromJson(linea,CPU_INFO::class.java)
        }
        else{
          var pos = s.indexOf(":")
          if(pos!=-1){
            var indice = s.substring(0,pos-1)
            if(agregar ==false){              // ------------------  Si no hay nada que agregar y nos encontramos "processor" iniciamos el string con la informacion
              if(indice == "processor"){
                infocore = infocore+ dividirLinea(s,":") +','
                agregar = true
              }
            }
            else{                           // -------------------- Si hay cosas que agregar
              if(indice == "processor"){        // -------------------- y nos encontramos procesador
                // -------------- Terminamos de crear el objeto core anterior
                infocore = "{"+infocore
                infocore = infocore.dropLast(1)
                infocore = infocore + "}"
                var core:CORE_INFO = Gson().fromJson(infocore,CORE_INFO::class.java)
                infoCPU.cores.add(core)// -------------- Al acabar lo que hacemos es agregar el objeto core dentro de InfoCPU

                infocore =""  // -------------- Seguimos con el siguiente core
                infocore = infocore+ dividirLinea(s,":") +','
              }
              if(indice != "processor"){       // -------------------- informacion del procesador actual
                infocore = infocore + dividirLinea(s,":") +','
              }
            }
          }
        }
      }
      infoCPU.coresNumber = infoCPU.cores.size
      return infoCPU
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
        println(linea)
        var pos = linea.indexOf(delimitador)
        val valor = linea.substring(pos+1)
        var indice = linea.substring(0,pos-1)
        indice = indice.replace(" ","")
        if(indice == "CPUarchitectur")
          indice = "CPUarchitecture"
        objeto ='"'+indice+'"'+':'+'"'+valor+'"'
      }
      return objeto
    }
  }





}
