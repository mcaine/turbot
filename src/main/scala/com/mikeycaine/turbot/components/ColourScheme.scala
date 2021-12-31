package com.mikeycaine.turbot.components

import scala.util.Random

object ColourScheme {

  val seed = 31337L
  val colours: Seq[Double] = for (i <- 1 to 10) yield {
    val r = new Random(seed + 10000 * i.toLong)
    0xaa0000 + r.nextGaussian() * 0x20000
    //0xff0000
  }
}
