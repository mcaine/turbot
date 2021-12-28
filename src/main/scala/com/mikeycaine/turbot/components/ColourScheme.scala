package com.mikeycaine.turbot.components

import scala.util.Random

object ColourScheme {
  val colours = for (i <- 1 to 10) yield {
    val r = new Random()
    0x000000 + r.nextGaussian() * 0x5000
    //0xff0000
  }
}