package com.example.myphoneoptimizer

class CORE_INFO (
  var processor :Int?=null,
  var BogoMIPS: Double?=null,
  var CPUimplementer: String?=null,
  var CPUvariant:String?=null,
  var CPUpart: String?=null,
  var CPUrevision: String?=null,
  var CPUarchitecture:String?=null) {
  var Features:String=""
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


//data class Parent(val name: String, val age: Int, val children: Set<Child>) {
//  override fun toString() = "Parent(\"$name\", $age, setOf(${children.joinToString()})"
//}