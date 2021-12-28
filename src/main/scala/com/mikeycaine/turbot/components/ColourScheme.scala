package com.mikeycaine.turbot.components

import scala.util.Random

object ColourScheme {
  val colours: Seq[Double] = for (i <- 1 to 10) yield {
    val r = new Random()
    0xaa0000 + r.nextGaussian() * 0x10000
    //0xff0000
  }
}
