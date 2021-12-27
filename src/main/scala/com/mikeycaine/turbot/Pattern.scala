package com.mikeycaine.turbot

case class Pattern (rows: Seq[String]) {

  assert( rows.size > 0)

}

object Pattern {
  def main(args: Array[String]): Unit = {
    println("Hello from main of class")
  }
}
