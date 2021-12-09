package com.example.myphoneoptimizer

class CPU_INFO(
var Processor: String="",
var coresNumber:Int=0,
var hardware:String?=null){
  var cores:MutableList<CORE_INFO> = arrayListOf()

  override fun toString(): String {
    var tmp = ""
    tmp = "Processor : " + Processor +"\n"
    tmp += "Cores Number : "+ cores.size + "\n"
    tmp += "Hardware : "+hardware+"\n"
    tmp += "Cores: "+cores
    return tmp
  }
}
