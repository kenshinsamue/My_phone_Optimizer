package com.example.myphoneoptimizer

/**
 * La clase CORE_INFO colecciona toda la informacion substraida de los Cores o Nucleos del terminal que ejecuta la APP
 *
 */
class CORE_INFO{
  var Features:String=""
  var processor :Int=0
  var BogoMIPS: Double=0.0
  var CPUimplementer: String=""
  var CPUvariant:String=""
  var CPUpart: String=""
  var CPUrevision: String=""
  var CPUarchitecture:String=""
  override fun toString(): String {
    var tmp = ""
    tmp +="Processor: "+processor+"\n"
    tmp +="BogoMips: "+BogoMIPS+"\n"
    tmp +="CPU Implementer: "+CPUimplementer+"\n"
    tmp +="CPU Variant: "+ CPUvariant+"\n"
    tmp +="CPU revision: "+CPUrevision+"\n"
    tmp +="CPU Architecture: "+CPUarchitecture+"\n"
    tmp +="CPU Part"+CPUpart+"\n"
    tmp +="CPU Features:  "+Features.replace(" ","\n\t")+"\n"
    return tmp
  }
}
